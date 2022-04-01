package com.ldmzw.demo.logback.test;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

//@Slf4j
public class MTest {

    @Test
    public void test1() {
        org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MTest.class);
        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger(MTest.class);
        Logger logger1 = loggerContext.getLogger("com.ldmzw.demo.logback.test");
        Logger logger2 = loggerContext.getLogger("com.ldmzw.demo.logback");
        Logger logger3 = loggerContext.getLogger("com.ldmzw.demo");
        Logger logger4 = loggerContext.getLogger("com.ldmzw");
        Logger logger5 = loggerContext.getLogger("com");
        // 修改等级
        logger1.setLevel(ch.qos.logback.classic.Level.toLevel("warn"));

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        logger2.setLevel(ch.qos.logback.classic.Level.toLevel("warn"));

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");
    }

    @Test
    public void test2() {
        Log log = LogFactory.getLog(MTest.class);
        log.trace("level is: trace");
        log.debug("level is: debug");
        log.info("level is: info");
        log.warn("level is: warn");
        log.error("level is: error");
    }

    @Test
    public void test3() {
        org.apache.log4j.Logger log4j = org.apache.log4j.LogManager.getLogger(MTest.class);
        log4j.info("level is: info");

        org.apache.logging.log4j.Logger log4j2 = org.apache.logging.log4j.LogManager.getLogger(MTest.class);
        log4j2.info("level is: info");
    }

    @Test
    public void test4() {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger(MTest.class.getName());
        log.info("level is: info");
        log.log(java.util.logging.Level.ALL, "level is: all");
        log.log(java.util.logging.Level.FINE, "level is: FINE");
    }

    @Test
    public void test5() {

    }

}
