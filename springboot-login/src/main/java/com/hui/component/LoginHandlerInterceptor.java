package com.hui.component;

import com.hui.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/12/1
 */

/**
 * 添加拦截器
 */

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        System.out.println(token);

        /**
         * 问题所在,为什么会报错
         */
        //JwtUtils==null;
        Claims claims = jwtUtils.parseToken(token);
        if (claims!=null){
            request.setAttribute("user_claims",claims);
            return true;
        }else {
            return false;
        }
        // System.out.println(" 使用了拦截器 preHandle");
        /*
        * 如果返回的是false,表示所有的请求都被拦截
        * 如果返回的是true,表示这个请求通过
        * */
       //return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
