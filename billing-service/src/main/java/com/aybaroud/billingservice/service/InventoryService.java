package com.aybaroud.billingservice.service;

import com.aybaroud.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/products")
    public PagedModel<Product> findAllProduct();

}
