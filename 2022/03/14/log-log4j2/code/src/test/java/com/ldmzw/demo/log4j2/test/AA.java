package com.ldmzw.demo.log4j2.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AA {

    public void test() {

        log.trace("AA level is: {}", "trace");
        log.debug("AA level is: {}", "debug");
        log.info("AA level is: {}", "info");
        log.warn("AA level is: {}", "warn");
        log.error("AA level is: {}", "error");

    }
}
