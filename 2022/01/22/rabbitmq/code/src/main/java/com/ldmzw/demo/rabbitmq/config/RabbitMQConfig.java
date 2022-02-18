package com.ldmzw.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String RABBITMQ_DIRECT_EXCHANGE = "test-direct-exchange";
    public static final String RABBITMQ_DIRECT_QUEUE = "test-direct-queue";
    public static final String RABBITMQ_DIRECT_ROUTING = "test-direct-routing";

    public static final String RABBITMQ_FANOUT_EXCHANGE = "test-fanout-exchange";
    public static final String RABBITMQ_FANOUT_QUEUE_1 = "test-fanout-queue-1";
    public static final String RABBITMQ_FANOUT_QUEUE_2 = "test-fanout-queue-2";
    public static final String RABBITMQ_FANOUT_QUEUE_3 = "test-fanout-queue-3";

    public static final String RABBITMQ_TOPIC_EXCHANGE = "test-topic-exchange";
    public static final String RABBITMQ_TOPIC_QUEUE_A = "test-topic-queue-a";
    public static final String RABBITMQ_TOPIC_QUEUE_B = "test-topic-queue-b";
    public static final String RABBITMQ_TOPIC_QUEUE_C = "test-topic-queue-c";

    public static final String RABBITMQ_HEADERS_EXCHANGE = "test-headers-exchange";
    public static final String RABBITMQ_HEADERS_QUEUE_A = "test-headers-queue-a";
    public static final String RABBITMQ_HEADERS_QUEUE_B = "test-headers-queue-b";
    public static final String RABBITMQ_HEADERS_QUEUE_C = "test-headers-queue-c";

    public Queue queue(String name) {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        return new Queue(name, true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        //Direct交换机
        return new DirectExchange(RABBITMQ_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        //Fanout交换机
        return new FanoutExchange(RABBITMQ_FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        //Topic交换机
        return new TopicExchange(RABBITMQ_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public HeadersExchange headersExchange() {
        //Headers交换机
        return new HeadersExchange(RABBITMQ_HEADERS_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(queue(RABBITMQ_DIRECT_QUEUE))
                //到交换机
                .to(directExchange())
                //并设置匹配键
                .with(RABBITMQ_DIRECT_ROUTING);
    }

    @Bean
    public Binding bindFanout1() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(queue(RABBITMQ_FANOUT_QUEUE_1))
                //到交换机
                .to(fanoutExchange());
    }

    @Bean
    public Binding bindFanout2() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(queue(RABBITMQ_FANOUT_QUEUE_2))
                //到交换机
                .to(fanoutExchange());
    }

    @Bean
    public Binding bindFanout3() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(queue(RABBITMQ_FANOUT_QUEUE_3))
                //到交换机
                .to(fanoutExchange());
    }


    @Bean
    public Binding bindTopicA() {
        //队列A绑定到TopicExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_TOPIC_QUEUE_A))
                .to(topicExchange())
                .with("*.a.*");
    }

    @Bean
    public Binding bindTopicB() {
        //队列B绑定到TopicExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_TOPIC_QUEUE_B))
                .to(topicExchange())
                .with("*.b.#");
    }

    @Bean
    public Binding bindTopicC() {
        //队列C绑定到TopicExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_TOPIC_QUEUE_C))
                .to(topicExchange())
                .with("rabbit.#");
    }

    @Bean
    public Binding bindHeadersA() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-x", "x");
        map.put("header-a", "a");
        //队列A绑定到HeadersExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_HEADERS_QUEUE_A))
                .to(headersExchange())
                .whereAll(map)
                .match();
    }

    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-x", "x");
        map.put("header-b", "b");
        //队列B绑定到HeadersExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_HEADERS_QUEUE_B))
                .to(headersExchange())
                .whereAll(map)
                .match();
    }

    @Bean
    public Binding bindHeadersC() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "a");
        map.put("header-b", "b");
        //队列C绑定到HeadersExchange交换机
        return BindingBuilder
                .bind(queue(RABBITMQ_HEADERS_QUEUE_C))
                .to(headersExchange())
                .whereAny(map)
                .match();
    }
}
