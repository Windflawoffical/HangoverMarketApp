package com.example.hangovermarketwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HangovermarketwebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangovermarketwebserviceApplication.class, args);
	}

}
