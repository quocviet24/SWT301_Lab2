package com.nishikatakagi.ProductDigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class ProductDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDigitalApplication.class, args);
	}

}
