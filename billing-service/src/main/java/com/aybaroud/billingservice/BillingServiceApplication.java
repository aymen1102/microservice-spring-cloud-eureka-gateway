package com.aybaroud.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerService customerService,
							InventoryService inventoryService){
		return args -> {
			Customer customer = customerService.getCustomerById(1L);
			Product product = inventoryService.findProductById(2L);
			Bill bill = billRepository.save(new Bill(null,new Date(),customer.getId(),customer,null));
			inventoryService.findAllProduct().getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null,p.getId(),30,product,p.getPrice(),bill));
			});
		};
	}
}
