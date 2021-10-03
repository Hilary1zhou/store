package com.cy.store.interceptor;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测全局session对象中是否有uid数据，没有代表用户没登录，拦截下来
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("uid") == null) {
            //session数据的uid为空，代表用户没有登录，重定向跳转到登录页面
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
