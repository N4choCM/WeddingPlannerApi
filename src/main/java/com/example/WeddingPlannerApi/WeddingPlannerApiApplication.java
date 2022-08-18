package com.example.WeddingPlannerApi;

import entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.UserService;

@SpringBootApplication
public class WeddingPlannerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingPlannerApiApplication.class, args);
	}

}
