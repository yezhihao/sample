package org.sample.seckill.config;

import org.sample.component.mybatis.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

}