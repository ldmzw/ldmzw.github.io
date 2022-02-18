package com.ldmzw.demo.rabbitmq.listener;

import com.ldmzw.demo.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_DIRECT_QUEUE)})
    @RabbitHandler
    public void consumerDirect(Map<String, Object> map) {
        log.info("direct consumer message is: {}", map);
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_FANOUT_QUEUE_1)})
    @RabbitHandler
    public void consumerFanout1(Map<String, Object> map) {
        log.info("fanout consumer1 message is: {}", map);
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_FANOUT_QUEUE_2)
            , @Queue(RabbitMQConfig.RABBITMQ_FANOUT_QUEUE_3)})
    @RabbitHandler
    public void consumerFanout2(Map<String, Object> map) {
        log.info("fanout consumer2 message is: {}", map);
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_TOPIC_QUEUE_A)})
    @RabbitHandler
    public void consumerTopic1(Map<String, Object> map) {
        log.info("topic consumer1 message is: {}", map);
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_TOPIC_QUEUE_B)
            , @Queue(RabbitMQConfig.RABBITMQ_TOPIC_QUEUE_C)})
    @RabbitHandler
    public void consumerTopic2(Map<String, Object> map) {
        log.info("topic consumer2 message is: {}", map);
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_HEADERS_QUEUE_A)})
    @RabbitHandler
    public void consumerHeaders1(Message message) {
        log.info("headers consumer1 message is: {}", new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_HEADERS_QUEUE_B)})
    @RabbitHandler
    public void consumerHeaders2(Message message) {
        log.info("headers consumer2 message is: {}", new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = {@Queue(RabbitMQConfig.RABBITMQ_HEADERS_QUEUE_C)})
    @RabbitHandler
    public void consumerHeaders3(Message message) {
        log.info("headers consumer3 message is: {}", new String(message.getBody()));
    }
}
