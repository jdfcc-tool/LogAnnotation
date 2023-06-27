package com.jdfcc.logannotation.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jdfcc.logannotation.constants.RabbitMqConstants.*;

/**
 * @author Jdfcc
 * @Description 日志队列配置
 * @DateTime 2023/6/26 14:03
 */
@Configuration
public class LogQueueConfig {


    /**
     * 定义队列
     */
    @Bean
    public Queue createLogQueue() {
        return QueueBuilder.durable(LogQueue).build();
    }

    /**
     * 定义交换机
     */
    @Bean
    public DirectExchange createLogExchange() {
        return ExchangeBuilder.directExchange(LogExchange).durable(true).build();
    }

    /**
     * 定义路由
     */
    @Bean
    public Binding createLogQueueBinding(@Qualifier("createLogQueue") Queue queue,
                                 @Qualifier("createLogExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(LogRoutingKey);
    }



}
