package com.user.register.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigRec {

    @Value("${rabbitmq.queue1.name}")
    private String queue1;

    @Value("${rabbitmq.exchange1.name}")
    private String exchange1;

    @Value("${rabbitmq.routing1.key}")
    private String routingKey1;

    @Bean
    public Queue queue1(){
        return new Queue(queue1);
    }

    @Bean
    public TopicExchange exchange1(){
        return new TopicExchange(exchange1);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1())
                .to(exchange1())
                .with(routingKey1);
    }


}
