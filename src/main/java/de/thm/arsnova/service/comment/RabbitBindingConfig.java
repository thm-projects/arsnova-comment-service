package de.thm.arsnova.service.comment;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Configuration
public class RabbitBindingConfig {

    static final String commandExchangeName = "comment.command";
    static final String createCommandQueueName = "comment.command.create";
    static final String patchCommandQueueName = "comment.command.patch";
    static final String updateCommandQueueName = "comment.command.update";

    static final Map<String, Object> commandMapper = new HashMap<String, Object>(){{
        put("create", createCommandQueueName);
        put("patch", patchCommandQueueName);
        put("update", updateCommandQueueName);
    }};

    @Bean
    @Autowired
    public Exchange exchange(RabbitAdmin rabbitAdmin) {
        final Exchange exchange = new FanoutExchange(commandExchangeName, true, false);

        rabbitAdmin.declareExchange(exchange);

        return exchange;
    }

    @Bean
    @Autowired
    public Queue createCommandQueueName(RabbitAdmin rabbitAdmin) {
        final Queue queue = new Queue(createCommandQueueName, true, false, false);

        rabbitAdmin.declareQueue(queue);

        return queue;
    }

    @Bean
    @Autowired
    public Queue patchCommandQueueName(RabbitAdmin rabbitAdmin) {
        final Queue queue = new Queue(patchCommandQueueName, true, false, false);

        rabbitAdmin.declareQueue(queue);

        return queue;
    }

    @Bean
    @Autowired
    public Queue updateCommandQueueName(RabbitAdmin rabbitAdmin) {
        final Queue queue = new Queue(updateCommandQueueName, true, false, false);

        rabbitAdmin.declareQueue(queue);

        return queue;
    }

    /*@Bean
    @Autowired
    public Binding binding(RabbitAdmin rabbitAdmin, Exchange exchange, Queue queue) {
        final Binding binding = new Binding(
                queue.getName(),
                Binding.DestinationType.QUEUE,
                exchange.getName(),
                "*",
                commandMapper
        );

        rabbitAdmin.declareBinding(binding);

        return binding;
    }*/

}