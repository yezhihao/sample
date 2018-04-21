package org.sample.seckill.controller;

import org.sample.model.APIResult;
import org.sample.model.enums.DefaultCodes;
import org.sample.model.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Created by Alan.ye on 2016/10/24.
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class.getSimpleName());

    @ExceptionHandler(APIException.class)
    @ResponseBody
    public APIResult onAPIException(APIException e) {
        return new APIResult(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResult onException(Exception e) {
        log.error("系统异常:", e);
        return new APIResult(e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public APIResult onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("系统异常:", e);
         return new APIResult(DefaultCodes.TypeMismatch, e);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    @ResponseBody
    public APIResult onHttpMessageNotReadableException(HttpMediaTypeException e) {
        log.warn("系统异常:", e);
        return new APIResult(DefaultCodes.NotSupportedType, e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public APIResult onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new APIResult(DefaultCodes.MissingParameter, ":" + e.getParameterName());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public APIResult MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new APIResult(DefaultCodes.TypeMismatch, ":" + e.getName());
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public APIResult onTypeMismatchException(TypeMismatchException e) {
        return new APIResult(DefaultCodes.TypeMismatch, ":" + e.getPropertyName());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public APIResult onHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new APIResult(DefaultCodes.NotImplemented, e);
    }

}