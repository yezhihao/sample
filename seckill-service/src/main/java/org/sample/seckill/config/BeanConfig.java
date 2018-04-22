package org.sample.seckill.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.sample.commons.lang.DateUtils;
import org.sample.component.mybatis.PageInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeJackson2ObjectMapper() {
        return builder -> {
            JavaTimeModule module = new JavaTimeModule();

            module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.DATE_TIME_FORMATTER));
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.DATE_TIME_FORMATTER));

            module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
            module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));

            builder.modules(module);
        };
    }
}