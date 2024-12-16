package com.example.listings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ListingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListingsApplication.class, args);
	}

}
