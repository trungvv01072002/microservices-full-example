package com.alden.notification;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq")
    @GeneratedValue(generator = "notification_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;
    private String sender;
    private LocalDateTime sentAt;
    private String toCustomerEmail;
    private Long toCustomerId;
}
