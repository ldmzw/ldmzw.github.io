package com.ldmzw.demo.rabbitmq.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ApplicationTest {

    @Autowired
    protected MockMvc mockMvc;

    @BeforeAll
    public static void init() {
        // 为所有测试加入初始条件

        log.info("environment has ready");
    }

    @Test
    public void testStartup() {
        log.info("server start up success");
    }

}
