package org.amirou.rabbitmqpublisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JsonConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());

            JSONObject jsonObject = new JSONObject(message);
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");
            String email = jsonObject.getString("email");
            System.out.printf("message : from = %s, to = %s, email = %s\n", from, to, email);
        };

        channel.basicConsume("Queue-1", true, deliverCallback, consumerTag -> {
        });
    }
}
