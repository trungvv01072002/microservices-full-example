package com.trungvv.springsleuth;

import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import zipkin2.reporter.AsyncReporter;

@Configuration
public class CloudConfig {

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}



	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
