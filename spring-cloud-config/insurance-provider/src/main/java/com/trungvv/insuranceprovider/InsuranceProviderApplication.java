package com.trungvv.insuranceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/insurance-service")
public class InsuranceProviderApplication {
    @GetMapping("/get-all-plans1")
    public List<String> getPlans(){
        return Stream.of("Premium", "Gold", "Silver", "Bronze").toList();
    }


    public static void main(String[] args) {
        SpringApplication.run(InsuranceProviderApplication.class, args);
    }

}
