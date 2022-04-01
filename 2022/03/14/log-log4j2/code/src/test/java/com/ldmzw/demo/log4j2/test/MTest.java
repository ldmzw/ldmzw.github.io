package com.ldmzw.demo.log4j2.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.jupiter.api.Test;

@Slf4j
public class MTest {

    @Test
    public void test() {

        AA a = new AA();
        a.test();

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) context.getLogger("com.ldmzw.demo.log4j2");
        // 修改等级
        logger.setLevel(Level.WARN);

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        logger.setLevel(Level.DEBUG);

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");
    }

    @Test
    public void test4() {
        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.config.LoggerConfig loggerConfig = context.getConfiguration().getLoggerConfig("com.ldmzw.demo");
        // 修改等级
        loggerConfig.setLevel(Level.WARN);
        // 更新日志
        context.updateLoggers();

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

    }

//    @Test
//    public void test1() {
//        Logger log = LoggerFactory.getLogger(MTest.class);
//        log.trace("level is: {}", "trace");
//        log.debug("level is: {}", "debug");
//        log.info("level is: {}", "info");
//        log.warn("level is: {}", "warn");
//        log.error("level is: {}", "error");
//    }
//
//    @Test
//    public void test2() {
//        Log log = LogFactory.getLog(MTest.class);
//        log.trace("level is: trace");
//        log.debug("level is: debug");
//        log.info("level is: info");
//        log.warn("level is: warn");
//        log.error("level is: error");
//    }
//
//    @Test
//    public void test3() {
//        org.apache.log4j.Logger log4j = org.apache.log4j.LogManager.getLogger(MTest.class);
//        log4j.info("level is: info");
//
//        org.apache.logging.log4j.Logger log4j2 = org.apache.logging.log4j.LogManager.getLogger(MTest.class);
//        log4j2.info("level is: info");
//    }

}
