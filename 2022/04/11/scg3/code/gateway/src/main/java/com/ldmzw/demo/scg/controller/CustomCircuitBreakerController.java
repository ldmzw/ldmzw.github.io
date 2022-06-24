package com.ldmzw.demo.scg.controller;

import com.ldmzw.demo.scg.constant.Constants;
import com.ldmzw.demo.scg.constant.MsgConstants;
import com.ldmzw.demo.scg.controller.info.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@Api(value = "CustomCircuitBreakerController", tags = "熔断")
public class CustomCircuitBreakerController {

    @ApiOperation(value = "默认熔断")
    @RequestMapping(value = Constants.PATH_CIRCUIT_BREAKER_FALLBACK)
    public Mono<ResponseVO<String>> circuitBreaker() {
        return Mono.just(new ResponseVO<>(MsgConstants.REMOTE_SERVER_ERROR));
    }

}
