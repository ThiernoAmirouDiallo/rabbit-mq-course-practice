package org.amirou.rabbitmqspringboot.rabbitmqspringboot.service;

import org.amirou.rabbitmqspringboot.rabbitmqspringboot.controller.entity.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {
    @RabbitListener(queues = {"Mobile"})
    public void consumeMessage(Person person) {
        System.out.printf("Consumed from rabbitmq person = %s\n", person);
    }
}
