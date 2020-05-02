package org.amirou.rabbitmqpublisher;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            String message = "This is for TV and Mobile";
            Map<String, Object> headersMap = new HashMap<>();
            headersMap.put("item1", "mobile");
            headersMap.put("item2", "television");

            BasicProperties basicProperties = new BasicProperties().builder().headers(headersMap).build();
            channel.basicPublish("HEADERS-EXCHANGE", "", basicProperties, message.getBytes());
        }
    }
}
