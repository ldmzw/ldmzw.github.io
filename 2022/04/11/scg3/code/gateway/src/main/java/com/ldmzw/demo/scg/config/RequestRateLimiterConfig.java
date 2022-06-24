package com.ldmzw.demo.scg.config;

import com.ldmzw.demo.scg.constant.Constants;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * 限流自定义
 */
@Configuration
public class RequestRateLimiterConfig {

    @Primary
    @Bean
    KeyResolver apiKeyResolver() {
        //按URL限流
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    @Bean
    KeyResolver userKeyResolver() {
        //按用户限流
        return exchange -> Mono.just(Optional.ofNullable(exchange.getRequest().getHeaders().get(Constants.REQUEST_HEADER_AUTHORIZATION)).map(e -> e.get(0)).orElse("none"));
    }

    @Bean
    KeyResolver ipKeyResolver() {
        //按IP来限流
        return exchange -> Mono.just(Optional.ofNullable(exchange.getRequest().getRemoteAddress()).map(InetSocketAddress::getHostName).orElse("none"));
    }

}
