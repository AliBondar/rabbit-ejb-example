package org.bondar.rabbitejbexample.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;

@Stateless
public class RabbitProducer {

    private static final String QUEUE_NAME = "exampleQueue";
    private static final int MESSAGE_COUNT = 10;
    private static final long DELAY_MS = 8000;

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

    public void sendMessagesInLoop(String baseMessage) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                for (int i = 1; i <= MESSAGE_COUNT; i++) {
                    String message = baseMessage + " - " + i;
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                    System.out.println(" [x] Sent: " + message);
                    Thread.sleep(DELAY_MS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
