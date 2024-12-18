package com.alden.notification.rabbitmq;

import com.alden.clients.notification.NotificationRequest;
import com.alden.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    // Listen event from the queue and send the notification
    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Received notification: {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
