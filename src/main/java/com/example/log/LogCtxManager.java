package com.example.log;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class LogCtxManager {

    private String clientIp;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        log.debug("########### clientIp : " + clientIp);

        MDC.put("clientIp", clientIp);

        this.clientIp = clientIp;
    }
}
