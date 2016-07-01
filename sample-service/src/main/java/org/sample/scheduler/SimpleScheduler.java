package org.sample.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduler {

    @Scheduled(cron = "0 30 12 * * ?")
    public void Test() {
        System.out.println("定时任务:" + new Date());
    }
}