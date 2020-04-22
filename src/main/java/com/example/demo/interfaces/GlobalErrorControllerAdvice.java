package com.example.demo.interfaces;

import com.example.demo.common.error.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class GlobalErrorControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public String errorHandler(Throwable ex) {
        StringBuilder errorStr = new StringBuilder();
        if (ex instanceof BusinessException) {
            //业务异常
            errorStr.append("业务异常：").append(ex.getMessage());
        } else {
            //系统异常
            errorStr.append("系统错误：").append(ex.getMessage());
        }
        return errorStr.toString();
    }
}
