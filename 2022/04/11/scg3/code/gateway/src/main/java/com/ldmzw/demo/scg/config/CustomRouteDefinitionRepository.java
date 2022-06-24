package com.ldmzw.demo.scg.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldmzw.demo.scg.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 路由信息自定义
 */
//@Component
@Slf4j
public class CustomRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final ObjectMapper om = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        log.info("开始刷新路由信息");

        //构建全局的GatewayFilterFactory
        List<FilterDefinition> filterDefinitionList = buildFilterDefinition();

        //构建路由信息
        List<RouteDefinition> routes = buildRouteDefinition(filterDefinitionList);

        return Flux.fromIterable(routes);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    /**
     * 构建全局的GatewayFilter
     *
     * @return
     */
    private List<FilterDefinition> buildFilterDefinition() {

        List<FilterDefinition> filterDefinitionList = new ArrayList<>();

        //限流
        FilterDefinition rateLimiterFilterDefinition = new FilterDefinition("RequestRateLimiter");
        rateLimiterFilterDefinition.getArgs().put("key-resolver", "#{@customKeyResolver}");
        rateLimiterFilterDefinition.getArgs().put("redis-rate-limiter.replenishRate", "10");
        rateLimiterFilterDefinition.getArgs().put("redis-rate-limiter.burstCapacity", "5000"); //5000 Qps
        filterDefinitionList.add(rateLimiterFilterDefinition);

        //熔断
        FilterDefinition circuitBreakerFilterDefinition = new FilterDefinition("CircuitBreaker");
        circuitBreakerFilterDefinition.getArgs().put("fallbackUri", "forward:" + Constants.PATH_CIRCUIT_BREAKER_FALLBACK);
        filterDefinitionList.add(circuitBreakerFilterDefinition);

        return filterDefinitionList;
    }

    /**
     * 构建页面路由信息
     *
     * @param filterDefinitionList
     * @return
     */
    private List<RouteDefinition> buildRouteDefinition(List<FilterDefinition> filterDefinitionList) {
        List<RouteDefinition> routes = new ArrayList<>();

        RouteDefinition definition = new RouteDefinition();
        definition.setId("scg.svc");
        definition.setOrder(1);
        definition.setUri(URI.create("http://localhost:8099"));

        //定义断言
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");
        predicate.getArgs().put("patterns", "/test/**");

        definition.getPredicates().add(predicate);

        definition.getFilters().addAll(filterDefinitionList);

        definition.getFilters().add(buildAddRequestHeaderGatewayFilterDefinition());

        routes.add(definition);

        return routes;
    }

    /**
     * 构建自定义GatewayFilter
     *
     * @return
     */
    private FilterDefinition buildAddRequestHeaderGatewayFilterDefinition() {
        FilterDefinition filterDefinition = new FilterDefinition("AddRequestHeader");
        filterDefinition.getArgs().put("name", "ldmzw-test-header");
        filterDefinition.getArgs().put("value", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return filterDefinition;
    }

}
