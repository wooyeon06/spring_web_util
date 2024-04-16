package com.example.async;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

@Log4j2
public class AsyncDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable task) {

        Map logCtx = MDC.getCopyOfContextMap();
        log.info("############## logCtx : " + logCtx);

        return () -> {
            if (logCtx != null) MDC.setContextMap(logCtx);
            task.run();
        };
    }
}
