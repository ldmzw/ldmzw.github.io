package com.ldmzw.demo.logback.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTest {

    private static final Logger log = LoggerFactory.getLogger(MTest.class);

    public static void main(String[] args) {
        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");
    }

}
