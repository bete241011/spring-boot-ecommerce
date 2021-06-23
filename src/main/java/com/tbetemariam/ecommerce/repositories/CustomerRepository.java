package com.tbetemariam.ecommerce.repositories;

import com.tbetemariam.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
