package com.wpt.controller;

import com.alibaba.fastjson.JSONArray;
import com.wpt.pojo.User;
import com.wpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ltq
 * @Date 2022/2/18 10:30
 */
@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String finUser(@RequestParam("userCode")String userCode,
                          @RequestParam("userPassword")String userPassword,
                          HttpSession session,
                          HttpServletRequest request){
        User user = userService.login(userCode, userPassword);
        if (user != null){
            session.setAttribute("userSession",user);
            return "frame";
        }
        request.getSession().setAttribute("msg","当前账号或密码错误");
        return "redirect:/index.jsp";
    }
    //退出
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userSession");
        return "redirect:/index.jsp";
    }

    //跳转到修改密码界面
    @GetMapping("/toUpdatePassword")
    public String toUpdatePassword(){
        return "pwdmodify";
    }

    /**
     * 验证旧密码是否正确
     * @param oldpassword
     * @param request
     * @return
     */
    @GetMapping("/verifyPwd")
    @ResponseBody
    public String verifyPwd(@RequestParam("oldpassword")String oldpassword,
                            HttpServletRequest request){
        //依旧从Session中获取ID
        User user = (User) request.getSession().getAttribute("userSession");
        //将结果存放在map集合中，让Ajax使用
        Map<String,String> resultMap = new HashMap<>();
        //下面开始判断，键都是用result，此处匹配js中的Ajax代码
        if (user == null){
            //说明session被移除，或为登录|已注销
            resultMap.put("result","sessionerror");
        }else if ("" == oldpassword  || oldpassword == null){
            resultMap.put("result","error");
        }else {
            //如果旧密码与前端传来的密码相同
            if (user.getUserPassword().equals(oldpassword)){
                resultMap.put("result","true");
            }else {
                //前端输入的密码和真实密码不相同
                resultMap.put("result","false");
            }
        }
        //JSONArray 阿里巴巴的JSON工具类 用途就是：转换格式
        return JSONArray.toJSONString(resultMap);
    }

    /**
     * 需改密码
     * @param newpassword
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/pwdmodify")
    public String pwdmodify(@RequestParam("newpassword")String newpassword,
                            HttpServletRequest request, Model model){
        //从Session获取Id
        User user = (User) request.getSession().getAttribute("userSession");
        if (user != null && newpassword != null){
            //修改密码并返回结果
            boolean flag = userService.updatePwd(user.getId(), newpassword);
            //如果密码修改成功，移除当前Session
            if (flag){
                model.addAttribute("message","修改密码成功，请使用新密码登录！");
                request.getSession().removeAttribute("userSession");
            }else {
                model.addAttribute("message","密码修改失败 新密码不符合规范");
            }
        }else {
            model.addAttribute("message","新密码不能为空!");
        }
        //修改完了 重定向到此修改页面
        return "pwdmodify";
    }


}
