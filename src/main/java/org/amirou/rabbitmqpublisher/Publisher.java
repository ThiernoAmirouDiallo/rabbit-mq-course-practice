package org.amirou.rabbitmqpublisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            String[] messages = new String[]{"first","second","third","fourht","fith","sixth","seventh","eighth","nineth","tenth"};
            for (String message : messages) {
                channel.basicPublish("", "Queue-1", null, message.getBytes());
            }
        }
    }
}
