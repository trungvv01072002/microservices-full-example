package com.trungvv.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@LoadBalancerClient(name = "CHAT-BOOK", configuration = CustomLoadBalancerConfiguration.class)
public class RibbonApplication {

	@Autowired
	@Lazy
	private RestTemplate restTemplate;


	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping("/ribbon")
	public String chatNow() {
		String url = "http://CHAT-BOOK/chatbook-application/chat";
		return restTemplate.getForObject(url, String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}
