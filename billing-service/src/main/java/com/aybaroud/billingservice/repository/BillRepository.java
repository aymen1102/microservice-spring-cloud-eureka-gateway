package com.aybaroud.billingservice.repository;

import com.aybaroud.billingservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository
        extends JpaRepository<Bill,Long> {
}
