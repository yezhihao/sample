package org.sample.seckill.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName = "MessageFilter", urlPatterns = "/*")
public class MessageFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MessageFilter.class.getSimpleName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilteredRequest filteredRequest = new FilteredRequest((HttpServletRequest) request);
        log.info(filteredRequest.readBody());
        chain.doFilter(filteredRequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    class FilteredRequest extends HttpServletRequestWrapper {

        private ServletInputStream inputStream;

        public String readBody() {
            return inputStream.readBody();
        }

        public FilteredRequest(HttpServletRequest request) throws IOException {
            super(request);
            inputStream = new ServletInputStream(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return inputStream;
        }
    }
}