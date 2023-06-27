package com.jdfcc.logannotation.config;

import com.jdfcc.logannotation.aop.LogAop;
import com.jdfcc.logannotation.consumer.LogQueueConsumer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jdfcc
 * @Description LogAnnotationConfig
 * @DateTime 2023/6/27 15:57
 */

@Configuration
@Slf4j
public class LogAnnotationConfig {


    public LogAnnotationConfig() {
        log.info("********* LogAnnotationConfig run successfully *********");
    }

    @Bean
    public LogAop logAop() {
        return new LogAop();
    }

    @Bean
    public LogQueueConsumer logQueueConsumer() {
        return new LogQueueConsumer();
    }
}
