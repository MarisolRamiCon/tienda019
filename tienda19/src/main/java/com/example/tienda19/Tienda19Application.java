package com.example.tienda19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Tienda19Application {

	public static void main(String[] args) {
		SpringApplication.run(Tienda19Application.class, args);
	}


}
