package com.alden.clients.notification;

import java.time.LocalDateTime;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerEmail,
        String message
       ) {
}
