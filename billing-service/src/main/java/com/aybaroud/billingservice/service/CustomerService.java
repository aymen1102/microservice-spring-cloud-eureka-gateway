package com.aybaroud.billingservice.service;

import com.aybaroud.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerService {
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id);
}
