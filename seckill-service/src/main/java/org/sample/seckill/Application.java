package org.sample.seckill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan("org.sample.seckill.mapper")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        log.info("***启动成功***");
    }
}