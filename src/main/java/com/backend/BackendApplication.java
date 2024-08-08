package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public ExecutorService executorService(){
		return Executors.newFixedThreadPool(4);
	}

	@Bean
	public WebClient webClient(WebClient.Builder webClientBuilder){
		return webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
	}

}
