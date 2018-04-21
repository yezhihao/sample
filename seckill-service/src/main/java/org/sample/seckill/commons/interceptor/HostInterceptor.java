package org.sample.seckill.commons.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**  */
public class HostInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
// return validate(request);
        return true;
    }

    @SuppressWarnings("unused")
    private boolean validate(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String source = request.getParameter("source");
        if (StringUtils.isBlank(source))
            return false;

        List<String> names = new ArrayList<>(parameterMap.size());

        Enumeration<String> pNames = request.getParameterNames();
        while (pNames.hasMoreElements())
            names.add(pNames.nextElement());
        Collections.sort(names);

        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            if ("source".equals(name))
                continue;
            String value = parameterMap.get(name)[0];
            sb.append(name).append("=").append(value).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
//        MD5Utils.validate(sb.toString(), source)
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}