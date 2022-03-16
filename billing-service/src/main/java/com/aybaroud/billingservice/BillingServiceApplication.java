package com.aybaroud.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository){
		return args -> {
			Bill bill1 = billRepository.save(new Bill(null,new Date(),1L,null,null));
			productItemRepository.save(new ProductItem(null,1L,30,null,500,bill1));
			productItemRepository.save(new ProductItem(null,2L,80,null,900,bill1));
			productItemRepository.save(new ProductItem(null,3L,50,null,200,bill1));
		};
	}
}
