package org.sample.web.config;

import org.sample.web.component.WebMvcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcInterceptor webMvcInterceptor() {
        return new WebMvcInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/user/login");
    }
}