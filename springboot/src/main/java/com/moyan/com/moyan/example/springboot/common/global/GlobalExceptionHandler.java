package com.moyan.com.moyan.example.springboot.common.global;

import com.moyan.com.moyan.example.springboot.constants.Response;
import com.moyan.com.moyan.example.springboot.constants.ResponseConstants;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public Response exceptionHandle(HttpRequest httpRequest, Exception e) {
        Response response = new Response(ResponseConstants.FAIL);
        return response;
    }
}
