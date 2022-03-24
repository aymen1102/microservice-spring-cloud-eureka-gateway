package com.aybaroud.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			/*use spring data REST dependency*/
			repositoryRestConfiguration.exposeIdsFor(Customer.class);
			customerRepository.save(new Customer(null,"Jack","jack@gmail.com"));
			customerRepository.save(new Customer(null,"Laure","laure@gmail.com"));
			customerRepository.save(new Customer(null,"Romain","romain@gmail.com"));
			customerRepository.findAll().stream().forEach(System.out::println);
		};
	}

}
