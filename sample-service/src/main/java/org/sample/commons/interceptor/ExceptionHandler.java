package org.sample.commons.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sample.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class.getSimpleName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        String result = null;

        if (exception instanceof APIException) {
            result = exception.toString();
            log.debug("业务异常:", exception);
        } else {
            result = builde(exception);
            log.error("系统出错:", exception);
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            log.error("转换异常信息失败", e);
        }
        return new ModelAndView();
    }

    public static String builde(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"");
        sb.append("code\":").append(500);
        sb.append(",\"message\":\"").append(e.getMessage());
        sb.append("\"}");
        return sb.toString();
    }

}