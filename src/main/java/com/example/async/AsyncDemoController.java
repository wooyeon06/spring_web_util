package com.example.async;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Log4j2
public class AsyncDemoController {

    private final AsyncService service;

    @RequestMapping("/async")
    @ResponseBody
    public Map async(HttpServletRequest request) {
        HashMap map = new HashMap();

        log.debug("call async controller !!!!!!");
        service.execute(map);

        map.put("RESULT", "SUCCESS");

        return map;
    }


}
