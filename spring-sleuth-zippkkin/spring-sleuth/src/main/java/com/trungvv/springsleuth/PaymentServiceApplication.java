package com.trungvv.springsleuth;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class PaymentServiceApplication {
	ObservationRegistry observationRegistry = ObservationRegistry.create();
	Observation observation = Observation.createNotStarted("sample", observationRegistry);

	@Autowired
	private RestTemplate template;

	@GetMapping("/getDiscount")
	public String discount() {
		observation.observe(() -> {
			log.info("discount service called....");
			return "added discount 15%";
		});
		return "fail";
	}

	@GetMapping("/payment")
	public String payment() {
		log.info("payment service called with discount....");
		return template.getForObject("http://localhost:8080/getDiscount", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
}
