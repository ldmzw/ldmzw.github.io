package com.ldmzw.demo.log4j2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/log/change/{name}/{level}")
    public String changeLevel(@PathVariable("name") String name, @PathVariable("level") String level) {

        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        Logger logger = logContext.getLogger(name);

        if (logger == null) {
            // 更改全局日志级别；否则按传递的包名或类名修改日志级别。
            logger = logContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
            name = "root";
        }
        logger.setLevel(Level.toLevel(level));
        log.info("修改日志等级：{} {}", name, level);
        return "ok";
    }

    @RequestMapping("/log")
    public String log() {
        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        return "ok - " + log.getName();
    }

}
