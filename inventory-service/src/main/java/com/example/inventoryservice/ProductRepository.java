package com.example.inventoryservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*exposing Spring repositories over REST
via Spring Data REST dependency*/
@RepositoryRestResource
interface ProductRepository
        extends JpaRepository<Product,Long> {
}
