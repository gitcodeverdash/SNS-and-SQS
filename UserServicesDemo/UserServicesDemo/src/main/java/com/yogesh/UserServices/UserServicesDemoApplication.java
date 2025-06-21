package com.yogesh.UserServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserServicesDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesDemoApplication.class, args);
	}

}
