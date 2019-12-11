package com.bringoz.driverscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Driverscore {

	public static void main(String[] args) {
		SpringApplication.run(Driverscore.class, args);
	}

	
	
}
