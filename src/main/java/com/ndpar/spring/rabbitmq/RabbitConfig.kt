package com.ndpar.spring.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun rabbitAdmin(@Autowired rabbitTemplate: RabbitTemplate) = RabbitAdmin(rabbitTemplate)
}
