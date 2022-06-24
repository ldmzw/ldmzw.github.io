package com.ldmzw.demo.scg.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomResponseHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomResponseHeaderGatewayFilterFactory.Config> {

    public CustomResponseHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {

            HttpHeaders headers = exchange.getResponse().getHeaders();

            headers.set("test-c", "" + System.currentTimeMillis());
            headers.set("test-d", exchange.getRequest().getId());
            headers.add("test-e", "eee");
            headers.remove("test-f");

        }));
    }

    public static class Config {

    }
}
