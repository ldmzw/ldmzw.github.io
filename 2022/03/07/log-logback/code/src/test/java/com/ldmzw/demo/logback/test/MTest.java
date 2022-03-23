package com.ldmzw.demo.logback.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTest {

    @Test
    public void test1() {
        Logger log = LoggerFactory.getLogger(MTest.class);
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

}
