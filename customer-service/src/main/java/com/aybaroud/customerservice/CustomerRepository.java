package com.aybaroud.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/*exposing Spring repositories over REST
via Spring Data REST dependency*/
@RepositoryRestResource
public interface CustomerRepository
        extends JpaRepository<Customer,Long> {
}
