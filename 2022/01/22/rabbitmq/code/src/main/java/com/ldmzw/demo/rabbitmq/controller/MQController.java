package com.ldmzw.demo.rabbitmq.controller;

import com.ldmzw.demo.rabbitmq.controller.info.MQMessage;
import com.ldmzw.demo.rabbitmq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/mq")
public class MQController {

    @Autowired
    private MQService mqService;

    @RequestMapping("/send")
    public String send(MQMessage message, HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.put(header, request.getHeader(header));
        }
        return mqService.send(message.getExchange(), message.getRouting(), message.getMsg(), headers);
    }

}
