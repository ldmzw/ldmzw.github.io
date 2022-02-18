package com.ldmzw.demo.rabbitmq.service;

import java.util.Map;

public interface MQService {

    String send(String exchange, String routing, String msg);

    String send(String exchange, String routing, String msg, Map<String, Object> headers);

}
