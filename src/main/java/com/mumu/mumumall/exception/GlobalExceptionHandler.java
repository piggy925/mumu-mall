package com.mumu.mumumall.exception;

import com.mumu.mumumall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultExceptionHandler(Exception e) {
        log.error("Default Exception", e);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object MallExceptionHandler(MallException e) {
        log.error("MallException", e);
        return new ApiRestResponse<>(e.getCode(), e.getMsg());
    }
}
