package org.amirou.rabbitmqspringboot.rabbitmqspringboot.controller;

import org.amirou.rabbitmqspringboot.rabbitmqspringboot.controller.entity.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class PublisherController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/publish/{name}")
    String postMessage(@PathVariable String name) {
        Person p = new Person(new Random().nextLong(), name);
        rabbitTemplate.convertAndSend("Mobile", p);
//        rabbitTemplate.convertAndSend("DIRECT-EXCHANGE", "tv", p);
//        rabbitTemplate.convertAndSend("FANOUT-EXCHANGE", "", p);
//        rabbitTemplate.convertAndSend("TOPIC-EXCHANGE", "ac.tv", p);
        return String.format("The message '%s' has been published to rabbitmq successfully", p);
    }

    @PostMapping("/publish/headers/{name}")
    String postMessageToHeadersExchange(@PathVariable String name) throws IOException {
        Person p = new Person(new Random().nextLong(), name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(p);
        objectOutputStream.flush();
        objectOutputStream.close();

        Message message = MessageBuilder
                .withBody(byteArrayOutputStream.toByteArray())
                .setHeader("item1", "mobile")
                .setHeader("item2", "television")
                .setContentType("application/x-java-serialized-object")
                .build();

        byteArrayOutputStream.close();

        rabbitTemplate.convertAndSend("HEADERS-EXCHANGE", "", message);
        return String.format("The message '%s' has been published to HEADERS-EXCHANGE successfully", p);
    }
}

