package com.wpt.controller;

import com.alibaba.fastjson.JSONArray;
import com.wpt.pojo.Provider;
import com.wpt.pojo.User;
import com.wpt.service.ProviderService;
import com.wpt.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 9:46
 */
@Controller
@RequestMapping("/Provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 根据查询条件查询供应商
     * @param queryProCode
     * @param queryProName
     * @param pageIndex
     * @param model
     * @return
     */
    @GetMapping("/toProviderPage")
    public String toProviderPage(@RequestParam(value = "queryProCode",required = false)String queryProCode,
                                 @RequestParam(value = "queryProName",required = false)String queryProName,
                                 @RequestParam(value = "pageIndex",required = false) Integer pageIndex,
                                 Model model){
        //获取总记录数
        int totalCount = providerService.getProviderCounts(queryProCode, queryProName);
        //获取当前页
        int currentPageNo = pageIndex != null ? pageIndex : 1;
        //封装page
        PageSupport page = new PageSupport();
        page.setCurrentPageNo(currentPageNo);  //当前页
        page.setPageSize(5); //页大小
        page.setTotalCount(totalCount);   //总记录数
        int form = (page.getCurrentPageNo() - 1) * page.getPageSize();
        //根据条件 查询供应商列表
        List<Provider> providerList = providerService.getProviderList(queryProCode, queryProName, form, page.getPageSize());
        //数据回显并发送给前端
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProCode",queryProCode);
        model.addAttribute("queryProName",queryProName);
        model.addAttribute("totalPageCount",page.getTotalPageCount());
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("currentPageNo",currentPageNo);
        return "providerlist";
    }

    /**
     * 跳转到添加供应商界面
     * @return
     */
    @GetMapping("/toAddProviderPage")
    public String toAddProviderPage(){
        return "provideradd";
    }

    /**
     * 添加供应商操作
     * @param provider
     * @param request
     * @return
     */
    @PostMapping("/addProvider")
    public String addProvider(Provider provider, HttpServletRequest request){
        provider.setCreatedBy(((User) request.getSession().getAttribute("userSession")).getId());
        provider.setCreationDate(new Date());
        int i = providerService.addProvider(provider);
        if (i > 0){
            return "redirect:/Provider/toProviderPage";
        }
        return "provideradd";
    }

    /**
     * 查看供应商信息操作
     * @param proId
     * @param model
     * @return
     */
    @GetMapping("/viewProvider/{proId}")
    public String viewProvider(@PathVariable Integer proId,Model model){
        Provider provider = providerService.queryProviderById(proId);
        model.addAttribute("provider",provider);
        return "providerview";
    }

    /**
     * 跳转到修改供应商界面并展示修改信息
     * @param proId
     * @param model
     * @return
     */
    @GetMapping("/toModifyProviderPage/{proId}")
    public String toModifyProviderPage(@PathVariable Integer proId,Model model){
        Provider provider = providerService.queryProviderById(proId);
        model.addAttribute("provider",provider);
        return "providermodify";
    }

    /**
     * 修改供应商信息操作
     * @param provider
     * @param request
     * @param proId
     * @return
     */
    @PostMapping("/modifyProvider")
    public String modifyProvider(Provider provider,HttpServletRequest request,
                                 @RequestParam("proid")Integer proId){
        provider.setId(proId);
        provider.setModifyBy(((User) request.getSession().getAttribute("userSession")).getId());
        provider.setModifyDate(new Date());
        boolean flag = providerService.updateProvider(provider);
        if (flag){
            return "redirect:/Provider/toProviderPage";
        }
        return "providermodify";
    }

    /**
     * 删除供应商信息操作
     * @param proId
     * @return
     */
    @GetMapping("/deleteProvider/{proId}")
    @ResponseBody
    public String deleteProvider(@PathVariable Integer proId){
        HashMap<String,String> map = new HashMap<>();
        if (proId <= 0){
            map.put("delResult","notexist");
        }else {
            boolean flag = providerService.deleteProvider(proId);
            if (flag){
                map.put("delResult","true");
            }else{
                map.put("delResult","false");
            }
        }
        return JSONArray.toJSONString(map);
    }

    /**
     * 订单 添加模块 需要查询供应商列表进行展示
     * @return
     */
    @GetMapping("/getProviderList")
    @ResponseBody
    public String getProviderList(){
        int providerCounts = providerService.getProviderCounts(null, null);
        List<Provider> providerList = providerService.getProviderList(null, null, 1, providerCounts);
        return JSONArray.toJSONString(providerList);
    }
}
