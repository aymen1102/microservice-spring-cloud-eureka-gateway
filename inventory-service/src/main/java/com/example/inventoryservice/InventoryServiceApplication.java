package com.example.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,
							RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			/* use spring data REST dependency */
			repositoryRestConfiguration.exposeIdsFor(Product.class);
			productRepository.save(new Product(null,"Computer",2600.0));
			productRepository.save(new Product(null,"Mouse",10.0));
			productRepository.save(new Product(null,"Keyboard",60.0));
		};
	}
}
