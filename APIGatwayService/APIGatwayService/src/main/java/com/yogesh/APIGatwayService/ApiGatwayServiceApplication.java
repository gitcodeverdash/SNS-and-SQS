package com.yogesh.APIGatwayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatwayServiceApplication.class, args);
	}

}
