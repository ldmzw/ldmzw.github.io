package com.ldmzw.demo.scg.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class CustomRequestHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomRequestHeaderGatewayFilterFactory.Config> {

    public CustomRequestHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().headers(headers -> {

                headers.set("test-c", "" + System.currentTimeMillis());
                headers.set("test-d", exchange.getRequest().getId());
                headers.add("test-e", "eee");
                headers.remove("test-f");

            }).build()).build());
        };
    }

    public static class Config {

    }
}
