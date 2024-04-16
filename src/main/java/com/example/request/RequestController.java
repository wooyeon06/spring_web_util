package com.example.request;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class RequestController {


    @RequestMapping("/request")
    @ResponseBody
    public Map request(@ModelAttribute UserDto userDto1, @RequestBody UserDto userDto2) {
        log.debug("userDto1 : " + userDto1);  //request param data
        log.debug("userDto2 : " + userDto2);  //request body data
        HashMap map = new HashMap();
        map.put("RESULT", "SUCCESS");
        return map;
    }
}
