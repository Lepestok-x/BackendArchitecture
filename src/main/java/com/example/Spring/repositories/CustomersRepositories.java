package com.example.Spring.repositories;

import com.example.Spring.database.Customers;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomersRepositories extends JpaRepository<Customers, Long> {
    @NullMarked
    Optional<Customers> findById(Long id);
}
