package com.meli.forecastApiRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.meli.forecast.controller")
public class ForecastApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastApiRestApplication.class, args);
	}

}

