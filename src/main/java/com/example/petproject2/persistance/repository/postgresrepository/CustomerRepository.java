package com.example.petproject2.persistance.repository.postgresrepository;

import com.example.petproject2.persistance.entity.PostgresEntity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}