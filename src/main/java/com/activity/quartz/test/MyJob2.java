package com.activity.quartz.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 有注解
 */
@Component
public class MyJob2 {

    @Scheduled(cron = "0/5 * * * * ?")
    public void execute() {
        System.out.println("执行注解实现的定时任务");
    }
}
