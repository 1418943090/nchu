package com.love.nchu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@EnableCaching
public class NchuApplication {
	public static void main(String[] args) {
		SpringApplication.run(NchuApplication.class, args);
	}
}
