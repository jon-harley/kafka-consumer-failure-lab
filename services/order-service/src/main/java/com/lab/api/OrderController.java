package com.lab.api;

import com.lab.events.OrderCreatedEvent;
import com.lab.events.OrderEventPublisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderEventPublisher publisher;

    public OrderController(OrderEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        String orderId = UUID.randomUUID().toString();
        String eventId = UUID.randomUUID().toString();

        var event = new OrderCreatedEvent(
                eventId,
                orderId,
                req.customerId(),
                req.amount(),
                Instant.now()
        );

        publisher.publishOrderCreated(event);

        return new CreateOrderResponse(orderId, eventId);
    }
}
