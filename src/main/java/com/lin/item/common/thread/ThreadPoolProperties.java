package com.lin.item.common.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: L
 * @Date: 2023/3/7 21:52
 * @Desc: 线程池配置
 */
@ConfigurationProperties("thread.pool")
@Component
@Data
public class ThreadPoolProperties {

    /** 核心线程数 */
    public Integer coreSize;

    /** 最大线程数 */
    public Integer maxSize;

    /** 线程存活时间 */
    public Integer KeepAliveTime;

    /** 最大队列数 */
    public Integer maxQueueSize;
}
