package com.lab.payments.consumer;

import com.lab.payments.events.OrderCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(topics = "orders.created")
    public void onMessage(ConsumerRecord<String, OrderCreatedEvent> record,
                          Acknowledgment ack) throws InterruptedException {

        var event = record.value();

        log.info("INSTANCE={} RECEIVED eventId={} orderId={} partition={} offset={}",
                System.getenv().getOrDefault("INSTANCE_ID", "local"),
                event.eventId(), event.orderId(), record.partition(), record.offset());

        Thread.sleep(30_000); // > max.poll.interval.ms (10s)

        log.info("DONE (but NOT acked) eventId={} orderId={}", event.eventId(), event.orderId());
    }
}