package com.aybaroud.billingservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface ProductItemRepository
        extends JpaRepository<ProductItem,Long> {
}
