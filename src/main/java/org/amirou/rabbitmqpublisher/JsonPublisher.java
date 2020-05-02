package org.amirou.rabbitmqpublisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JsonPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("from", LocalDate.now().minus(20, ChronoUnit.DAYS));
            jsonObject.put("to", LocalDate.now().minus(1, ChronoUnit.DAYS));
            jsonObject.put("email", "thiernoamirou@diallo.com");
            channel.basicPublish("", "Queue-1", null, jsonObject.toString().getBytes());

//            String[] messages = new String[]{"first","second","third","fourht","fith","sixth","seventh","eighth","nineth","tenth"};
//            JSONArray jsonArray = new JSONArray(messages);
//            channel.basicPublish("", "Queue-1", null, jsonArray.toString().getBytes());
        }
    }
}
