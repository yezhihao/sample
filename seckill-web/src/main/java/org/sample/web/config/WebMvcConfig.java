package org.sample.web.config;

import org.sample.web.component.WebMvcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Qualifier("contextPath")
    @Autowired
    String contextPath;

    @Bean
    public WebMvcInterceptor webMvcInterceptor() {
        return new WebMvcInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcInterceptor()).addPathPatterns(contextPath + "/**")
                .excludePathPatterns(contextPath + "/user/**");
    }
}