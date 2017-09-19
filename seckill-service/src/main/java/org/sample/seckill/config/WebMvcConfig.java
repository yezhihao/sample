package org.sample.seckill.config;

import org.sample.seckill.commons.interceptor.HostInterceptor;
import org.sample.seckill.commons.interceptor.MessageFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ServletComponentScan(basePackageClasses = MessageFilter.class)
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hostInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HostInterceptor hostInterceptor() {
        return new HostInterceptor();
    }

}