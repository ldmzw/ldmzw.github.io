package com.ldmzw.demo.rabbitmq.controller.info;

import lombok.Data;

@Data
public class MQMessage {

    private String exchange;
    private String routing;
    private String msg;

}
