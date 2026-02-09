package com.lab.events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String ordersCreatedTopic;

    public OrderEventPublisher(KafkaTemplate<String, Object> kafkaTemplate, @Value("${app.topics.orders-created}") String ordersCreatedTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.ordersCreatedTopic = ordersCreatedTopic;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        // key = orderId ajuda a manter ordenação por pedido (por partição)
        kafkaTemplate.send(ordersCreatedTopic, event.orderId(), event);
    }
}
