package com.trungvv.insuranceclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/insurance-client")
@RefreshScope
public class InsuranceClientApplication {
    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${insurance.provider.url}")
    private String url;

    @Value("${insurance.client.message}")
    private String message;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/getAllPlans")
    public List<String> getPlans(){
        System.out.println("URL: " + url);
        System.out.println("Message: " + message);
        return restTemplate.getForObject(url, List.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InsuranceClientApplication.class, args);
    }

}
