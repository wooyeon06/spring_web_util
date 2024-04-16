package com.example.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DemoListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println(this.getClass() + ":::contextDestroyed End.");
    }

    private void init() {
        System.out.println(this.getClass() + ":::init: context 초기화를 시작 합니다. @@@@@");
        System.out.println(this.getClass() + ":::init: context 초기화를 성공하였습니다.");
    }

    public void destroy() {
        System.out.println(this.getClass() + ":::destroy: context 종료화를 시작 합니다.");
        System.out.println(this.getClass() + ":::destroy: context 종료화를 성공하였습니다.");

    }

}


