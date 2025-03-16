package org.bondar.rabbitejbexample.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.bondar.rabbitejbexample.dao.MessageDao;
import org.bondar.rabbitejbexample.entity.Message;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Singleton
@Startup
public class RabbitConsumer {

    @EJB
    private MessageDao messageDao;

    private static final String QUEUE_NAME = "exampleQueue";

    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

                System.out.println(" [x] Received '" + message + "'");
                boolean success = persistMessage(message);

                if (success) {
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    System.out.println(" [✔] Message saved and acknowledged: " + message);
                } else {
                    System.err.println(" [❌] Message NOT saved, will not acknowledge!");
                }
            };

            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private boolean persistMessage(String content) {
        try {
            Message message = new Message();
            message.setContent(content);
            message.setProcessedSuccessfully(true);

            messageDao.create(message);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Database Error: " + e.getMessage());
            return false;
        }
    }
}

