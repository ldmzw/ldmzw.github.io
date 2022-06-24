package com.ldmzw.demo.scg.filter;

import com.ldmzw.demo.scg.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    public static final int ORDER = -1;

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final Long startTime = System.currentTimeMillis();
        String instanceId = String.valueOf(startTime);

        // 获取请求路径
        final String path = request.getPath().value();
        String host = request.getURI().getHost();
        HttpMethod method = request.getMethod();
        log.info("[{}] 进入网关, method: {}, path: {}, host: {}", instanceId, method, path, host);
        exchange.getAttributes().put(Constants.REQUEST_ATTRIBUTE_START_TIME, startTime);
        exchange.getAttributes().put(Constants.REQUEST_ATTRIBUTE_INSTANCE_ID, instanceId);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("[{}] 退出网关, path: {}, time: {} ms:", exchange.getAttribute(Constants.REQUEST_ATTRIBUTE_INSTANCE_ID), path, System.currentTimeMillis() - startTime)));
    }
}
