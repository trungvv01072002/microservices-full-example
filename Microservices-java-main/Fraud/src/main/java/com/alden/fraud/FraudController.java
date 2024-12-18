package com.alden.fraud;

import com.alden.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/fraud")
@Slf4j
@RestController
@AllArgsConstructor
public class FraudController {
    private final FraudCheckServices fraudCheckServices;

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        log.info("Checking if customer {} is a fraudster", customerId);
        return new FraudCheckResponse(fraudCheckServices.isFraudulentCustomer(customerId));
    }
}
