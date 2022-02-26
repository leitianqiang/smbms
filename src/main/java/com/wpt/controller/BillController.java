package com.wpt.controller;

import com.alibaba.fastjson.JSONArray;
import com.wpt.pojo.Bill;
import com.wpt.pojo.Provider;
import com.wpt.pojo.User;
import com.wpt.service.BillService;
import com.wpt.service.ProviderService;
import com.wpt.util.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ltq
 * @Date 2022/2/25 11:36
 */
@Controller
@RequestMapping("/Bill")
public class BillController {

    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    /**
     * 根据条件查询订单所有数据
     * @param queryProductName
     * @param queryProviderId
     * @param queryIsPayment
     * @param pageIndex
     * @param model
     * @return
     */
    @GetMapping("/toBillManagerPage")
    public String toBillManagerPage(@RequestParam(value = "queryProductName",required = false) String queryProductName,
                                    @RequestParam(value = "queryProviderId",required = false) Integer queryProviderId,
                                    @RequestParam(value = "queryIsPayment",required = false) Integer queryIsPayment,
                                    @RequestParam(value = "pageIndex",required = false) Integer pageIndex,
                                    Model model){
        //根据商品名称、供应商id、是否付款 查询订单总数
        int totalCount = billService.getBillCount(queryProductName,queryProviderId,queryIsPayment);
        int currentPageNo = pageIndex != null ? pageIndex : 1;
        //设置Page参数
        PageSupport page = new PageSupport();
        page.setCurrentPageNo(currentPageNo);
        page.setPageSize(5);
        page.setTotalCount(totalCount);
        int form = (page.getCurrentPageNo() - 1) * page.getPageSize();
        //根据条件查询订单列表
        List<Bill> billList = billService.getBillList(queryProductName, queryProviderId, queryIsPayment, form, page.getPageSize());

        //查询所有供应商
        int providerCounts = providerService.getProviderCounts(null, null);
        List<Provider> providerList = providerService.getProviderList(null, null, 1, providerCounts);

        //数据回显并传送到前端进行展示
        model.addAttribute("billList",billList);
        model.addAttribute("providerList",providerList);
        model.addAttribute("queryProductName",queryProductName);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        model.addAttribute("totalPageCount",page.getTotalPageCount());
        model.addAttribute("totalCount",page.getTotalCount());
        model.addAttribute("currentPageNo",page.getCurrentPageNo());
        return "billlist";
    }

    /**
     * 跳转到新增订单界面
     * @return
     */
    @GetMapping("/toAddBillPage")
    public String toAddBillPage(){
        return "billadd";
    }

    /**
     * 新增订单操作
     * @param bill
     * @param request
     * @return
     */
    @PostMapping("/addBill")
    public String addBill(Bill bill, HttpServletRequest request){
        bill.setCreatedBy(((User) request.getSession().getAttribute("userSession")).getId());
        bill.setCreationDate(new Date());
        boolean flag = billService.addBill(bill);
        if (flag){
            return "redirect:/Bill/toBillManagerPage";
        }
        return "billadd";
    }

    /**
     * 根据订单id查询订单信息
     * @param billid
     * @param model
     * @return
     */
    @GetMapping("/viewBill/{billid}")
    public String viewBill(@PathVariable Integer billid, Model model){
        Bill bill = billService.queryBillById(billid);
        model.addAttribute("bill",bill);
        return "billview";
    }

    /**
     *跳转到订单信息界面
     * @param billid
     * @param model
     * @return
     */
    @GetMapping("/toModifyBillPage/{billid}")
    public String toModifyBillPage(@PathVariable Integer billid,Model model){
        Bill bill = billService.queryBillById(billid);
        model.addAttribute("bill",bill);
        return "billmodify";
    }

    /**
     * 修改订单操作
     * @param billid
     * @param bill
     * @param request
     * @return
     */
    @PostMapping("/modifyBill")
    public String modifyBill(@RequestParam("billid") Integer billid,
                             Bill bill,
                             HttpServletRequest request){
        bill.setId(billid);
        bill.setModifyBy(((User) request.getSession().getAttribute("userSession")).getId());
        bill.setModifyDate(new Date());
        boolean flag = billService.updateBill(bill);
        if (flag){
            return "redirect:/Bill/toBillManagerPage";
        }
        return "billmodify";
    }

    /**
     * 删除订单操作
     * @param billid
     * @return
     */
    @GetMapping("/deleteBill/{billid}")
    @ResponseBody
    public String deleteBill(@PathVariable Integer billid){
        HashMap<String,String> map = new HashMap<>();
        boolean flag = billService.deleteBill(billid);
        if (billid <= 0){
            map.put("delResult","notexist");
        }else if (flag){
            map.put("delResult","true");
        }else {
            map.put("delResult","false");
        }
        return JSONArray.toJSONString(map);
    }
}
