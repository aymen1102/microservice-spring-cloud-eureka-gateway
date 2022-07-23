package com.aybaroud.billingservice;

import com.aybaroud.billingservice.model.Bill;
import com.aybaroud.billingservice.model.Customer;
import com.aybaroud.billingservice.model.Product;
import com.aybaroud.billingservice.model.ProductItem;
import com.aybaroud.billingservice.repository.BillRepository;
import com.aybaroud.billingservice.repository.ProductItemRepository;
import com.aybaroud.billingservice.service.CustomerService;
import com.aybaroud.billingservice.service.InventoryService;
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
public class  BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							ProductItemRepository productItemRepository,
							CustomerService customerService,
							InventoryService inventoryService){
		return args -> {
			// Loaded from another micro service using openfeign
			Customer customer = customerService.getCustomerById(1L);
			Product product = inventoryService.findProductById(2L);

			// create new bill
			Bill bill = billRepository.save(new Bill(null,new Date(),customer.getId(),customer,null));


			inventoryService.findAllProduct().getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null,p.getId(),30,product,p.getPrice(),bill));
			});
		};
	}
}
