package com.example.interceptor;

import com.example.log.LogCtxManager;
import com.example.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;


@Log4j2
public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        LogCtxManager logCtxManager = new LogCtxManager();
        logCtxManager.setClientIp(WebUtils.getRemoteIP(request));


        return true;
    }




}
