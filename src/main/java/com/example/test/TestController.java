package com.example.test;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequestMapping("/test")
@Controller
public class TestController {

    @RequestMapping("/1")
    public String test1(@RequestParam(value="dividend") int dividend, @RequestParam(value="divisor") int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("나누는 수는 0일 수 없습니다.");
        }
        return "나눗셈 결과: " + (dividend / divisor);
    }


}
