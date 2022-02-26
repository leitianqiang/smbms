package com.wpt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ltq
 * @Date 2022/2/21 9:12
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //用户登录成功
        if (request.getSession().getAttribute("userSession") != null){
            return true;
        }
        //用户请求登录需求
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("logout")){
            return true;
        }
        //其余请求都是error
        System.out.println("拦截到非法请求...");
        request.setAttribute("msg","请先登录！");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
        return false;
    }
}
