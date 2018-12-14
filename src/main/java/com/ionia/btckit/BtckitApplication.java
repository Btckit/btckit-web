package com.ionia.btckit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BtckitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtckitApplication.class, args);
	}
}
