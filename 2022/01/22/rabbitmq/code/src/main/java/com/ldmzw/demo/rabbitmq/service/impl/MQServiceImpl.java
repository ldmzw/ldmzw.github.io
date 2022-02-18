package com.ldmzw.demo.rabbitmq.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldmzw.demo.rabbitmq.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MQServiceImpl implements MQService {

    public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String send(String exchange, String routing, String msg) {
        return send(exchange, routing, msg, null);
    }

    @Override
    public String send(String exchange, String routing, String msg, Map<String, Object> headers) {
        MessageProperties messageProperties = new MessageProperties();
        //消息持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        if (headers != null) {
            messageProperties.getHeaders().putAll(headers);
        }

        //添加消息
        Map<String, Object> m = new HashMap<>();
        m.put("id", UUID.randomUUID());
        m.put("time", LocalDateTime.now().format(df));
        m.put("msg", msg);

        try {
            rabbitTemplate.convertAndSend(exchange, routing, new Message(om.writeValueAsBytes(m), messageProperties));
        } catch (JsonProcessingException e) {
            log.error("发送消息失败", e);
        }

        return "ok";
    }
}
