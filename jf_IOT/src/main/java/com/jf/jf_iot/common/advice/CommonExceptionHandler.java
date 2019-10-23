package com.jf.jf_iot.common.advice;

import com.jf.jf_iot.common.entity.ExceptionResult;
import com.jf.jf_iot.common.enums.ExceptionEnum;
import com.jf.jf_iot.common.exception.IOTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//设置注解。默认拦截所有的Controller
@ControllerAdvice
public class CommonExceptionHandler {
    //注解表示拦截异常，并拦截运行时异常
    @ExceptionHandler(IOTException.class)
    public ResponseEntity<ExceptionResult> handleException(IOTException e){
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        return ResponseEntity.status(exceptionEnum.getCode()).body(new ExceptionResult(exceptionEnum));
    }
}
