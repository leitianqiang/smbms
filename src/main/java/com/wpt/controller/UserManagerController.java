package com.wpt.controller;

import com.alibaba.fastjson.JSONArray;
import com.wpt.pojo.Role;
import com.wpt.pojo.User;
import com.wpt.service.RoleService;
import com.wpt.service.UserService;
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
 * @Date 2022/2/21 9:55
 */
@Controller
@RequestMapping("/UserManager")
public class UserManagerController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     *
     * @param queryName
     * @param queryUserRole
     * @param pageIndex
     * @param model
     * @return
     */
    @GetMapping("/toUserManagerPage")
    public String toUserManagerPage(@RequestParam(value = "queryName",required = false) String queryName,
                                    @RequestParam(value = "queryUserRole",required = false) Integer queryUserRole,
                                    @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                                    Model model) {
        //根据条件查询总记录数
        int totalCount = userService.getUserCounts(queryName, queryUserRole);
        //获取当前页
        int currentPageNo = pageIndex != null ? pageIndex : 1;
        //封装page类
        PageSupport page = new PageSupport();
        page.setCurrentPageNo(currentPageNo);  //当前页
        page.setPageSize(5);   //页大小
        page.setTotalCount(totalCount);  //总数量
        int from = (page.getCurrentPageNo() - 1) * page.getPageSize();
        //根据条件 查询用户列表
        List<User> userList = userService.getUserList(queryName, queryUserRole, from, page.getPageSize());
        //查询所有角色信息
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("userList",userList);
        model.addAttribute("roleList",roleList);
        model.addAttribute("queryUserName",queryName);
        model.addAttribute("queryUserRole",queryUserRole);
        model.addAttribute("totalPageCount",page.getTotalPageCount());
        model.addAttribute("totalCount",page.getTotalCount());
        model.addAttribute("currentPageNo",page.getCurrentPageNo());
        return "userlist";
    }

    /**
     *
     * @param uid
     * @param model
     * @return
     */
    @GetMapping("/toViewUser/{uid}")
    public String toViewUser(@PathVariable Integer uid,Model model){
        //根据id查询用户信息
        User user = userService.findById(uid);
        //将用户信息传至前端展示
        model.addAttribute("user",user);
        return "userview";
    }

    /**
     * 查询用户信息并跳转到修改页面
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/toModifyUserPage/{userId}")
    public String toModifyUserPage(@PathVariable Integer userId,Model model){
        User user = userService.findById(userId);
        model.addAttribute("user",user);
        return "usermodify";
    }


    /**
     * 下拉框获取用户信息
     * @return
     */
    @GetMapping("/getRoleList")
    @ResponseBody
    public String getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        //System.out.println("roleList" + roleList);
        return JSONArray.toJSONString(roleList);
    }

    /**
     *修改用户操作
     * @param user
     * @param request
     * @param uid
     * @return
     */
    @RequestMapping(value = "/modifyUser",method = RequestMethod.POST)
    public String modifyUser(User user,HttpServletRequest request,@RequestParam("uid") Integer uid){
        user.setId(uid);
        user.setModifyBy(((User) request.getSession().getAttribute("userSession")).getId());
        user.setModifyDate(new Date());
        //修改操作
        boolean flag = userService.modify(user);
        if (flag){
            return "redirect:/UserManager/toUserManagerPage";
        }
        return "usermodify";
    }

    /**
     * 删除用户操作
     * @param uid
     * @return
     */
    @GetMapping("/deleteUser/{uid}")
    @ResponseBody
    public String deleteUser(@PathVariable Integer uid){
        HashMap map = new HashMap();
        boolean flag = userService.deleteUser(uid);
        if (uid <= 0){
            map.put("delResult","notexist");
        }else if (flag){
            map.put("delResult","true");
        }else {
            map.put("delResult","false");
        }
        return JSONArray.toJSONString(map);
    }

    /**
     * 跳转到添加用户界面
     * @return
     */
    @GetMapping("/toAddUserPage")
    public String toAddUserPage(){
        return "useradd";
    }

    /**
     * 添加用户操作
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/addUser")
    public String addUser(User user,HttpServletRequest request){
        user.setCreatedBy(((User)request.getSession().getAttribute("userSession")).getId());
        user.setCreationDate(new Date());
        boolean flag = userService.addUser(user);
        if (flag){
            //说明执行成功 到 用户管理页面(即 查询全部用户列表)
            return "redirect:/UserManager/toUserManagerPage";
        }
        //说明 添加失败 回到 添加页面
        return "useradd";
    }

    /**
     * 验证是否存在 此userCode
     * @param userCode
     * @return
     */
    @GetMapping("/ifExistUserCode/{userCode}")
    @ResponseBody
    public String ifExistUserCode(@PathVariable String userCode){
        //根据此 userCode 查询是否有这个用户
        boolean flag = userService.ifExistUserCode(userCode);
        HashMap<String,String> map = new HashMap<>();
        System.out.println("==================userCode" + userCode);
        if ("" == userCode || userCode == null){
            map.put("userCode","NoWrite");
        }else if (flag){
            map.put("userCode","exist");
        }
        return JSONArray.toJSONString(map);
    }
}
