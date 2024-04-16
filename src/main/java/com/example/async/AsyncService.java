package com.example.async;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.Future;

@Log4j2
@Service
public class AsyncService  {

    @Async("threadPoolTaskExecutor")
    public Future<HashMap> execute(HashMap map) {
        log.debug("called async");

        try {
            log.debug("thread name : " + Thread.currentThread().getName());
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        map.put("RESULT", "SUCCESS");

        return new AsyncResult<HashMap>(map);
    }





}
