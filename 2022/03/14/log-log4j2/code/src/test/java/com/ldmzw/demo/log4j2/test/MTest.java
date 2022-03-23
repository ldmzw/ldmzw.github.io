package com.ldmzw.demo.log4j2.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

@Slf4j
public class MTest {

    public static void main(String[] args) {

        AA a = new AA();
        a.test();

        log.trace("level is: {}", "trace");
        log.debug("level is: {}", "debug");
        log.info("level is: {}", "info");
        log.warn("level is: {}", "warn");
        log.error("level is: {}", "error");

        org.apache.logging.log4j.core.LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) context.getLogger("com.ldmzw.demo.log4j2");
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
