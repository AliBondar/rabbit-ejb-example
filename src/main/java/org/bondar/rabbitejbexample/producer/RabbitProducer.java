package org.bondar.rabbitejbexample.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;

@Stateless
public class RabbitProducer {

    private static final String QUEUE_NAME = "exampleQueue";

    public void sendMessage(String message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
