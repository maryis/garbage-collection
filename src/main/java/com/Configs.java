package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class Configs {

    @Bean
    ExecutorService executorService() {
        ExecutorService mainPool = Executors.newFixedThreadPool(2);
        return mainPool;
    }

    @Bean
    ScheduledExecutorService scheduledExecutorService() {
        ScheduledExecutorService gsPool = Executors.newScheduledThreadPool(2);
        return gsPool;
    }
}
