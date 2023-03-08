package com.lin.item.common.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: L
 * @Date: 2023/3/7 21:52
 * @Desc: 生成不同类型的线程池
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolProperties pool) {
        return new ThreadPoolExecutor(
                pool.getCoreSize()
                , pool.getMaxSize()
                , pool.getKeepAliveTime()
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(pool.getMaxQueueSize())
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
