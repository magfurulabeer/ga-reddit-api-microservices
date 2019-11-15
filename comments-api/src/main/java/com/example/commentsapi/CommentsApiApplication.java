package com.example.commentsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
public class CommentsApiApplication {

	@RequestMapping("/")
	public String home() {
		return "some comments";
	}

	public static void main(String[] args) {
		SpringApplication.run(CommentsApiApplication.class, args);
	}

}
