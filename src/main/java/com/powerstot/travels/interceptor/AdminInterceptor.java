package com.powerstot.travels.interceptor;

import com.powerstot.travels.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getServletContext().getAttribute("USER");
        if (user != null) {
            return true;
        } else {
            // response.sendRedirect("http://localhost:8080/login.html");
            return false;
        }
    }
}
