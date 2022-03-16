package com.aybaroud.billingservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
class ProductItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    @Transient
    private Product product;
    private double price;
    @ManyToOne
    private Bill bill;
}