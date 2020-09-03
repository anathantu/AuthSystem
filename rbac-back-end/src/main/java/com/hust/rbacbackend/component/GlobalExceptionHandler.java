package com.hust.rbacbackend.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultInfo handler(Exception e){
        log.error("运行时异常----------",e);
        return ResultInfo.failed(400,e.getMessage(),null);
    }
}
