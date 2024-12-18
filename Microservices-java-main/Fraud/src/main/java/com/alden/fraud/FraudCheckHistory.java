package com.alden.fraud;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(name = "fraud_id_seq", sequenceName = "fraud_id_seq")
    @GeneratedValue(generator = "fraud_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
