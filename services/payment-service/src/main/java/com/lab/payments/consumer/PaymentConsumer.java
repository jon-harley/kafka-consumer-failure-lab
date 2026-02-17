package com.lab.payments.consumer;

import com.lab.payments.events.OrderCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {
    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(topics = "orders.created")
    public void onMessage(ConsumerRecord<String, OrderCreatedEvent> record) throws InterruptedException {
        OrderCreatedEvent event = record.value();

        log.info("RECEIVED eventId={} orderId={} partition={} offset={} key={}",
                event.eventId(), event.orderId(), record.partition(), record.offset(), record.key());

        //  Simula processamento lento (vai ajudar a estourar max.poll.interval depois)
        Thread.sleep(30_000);

        //  Simula "side effect" (por enquanto só log)
        log.info("PROCESSED eventId={} orderId={} (naive)", event.eventId(), event.orderId());
    }
}