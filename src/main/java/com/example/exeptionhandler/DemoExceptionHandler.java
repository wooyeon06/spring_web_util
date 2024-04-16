package com.example.exeptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice // 전역 예외 처리를 위한 어노테이션
public class DemoExceptionHandler {

    @ExceptionHandler(Exception.class) // 예외 타입을 지정하여 처리할 메소드를 정의합니다.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 응답 상태 코드를 설정합니다.
    @ResponseBody // 응답 본문을 설정합니다.
    public String handleException(Exception e) {
        return "예외가 발생했습니다: " + e.getMessage();
    }
}
