package com.ldmzw.demo.scg.svc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/print")
    public String print(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            log.info("request header ==> {}: {}", element, request.getHeader(element));
        }

        return "ok";
    }

    @RequestMapping("/header/**")
    public String header(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            log.info("request header ==> {}: {}", element, request.getHeader(element));
        }

        response.setHeader("test", request.getHeader("test"));
        response.setHeader("Location", request.getHeader("test"));

        return "ok";
    }


}
