package com.example.Spring.service;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mapper.MapStruct;
import com.example.Spring.repositories.CustomersRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServiceCustomerRead {
    private final CustomersRepositories customersRepositories;
    private final MapStruct mapStruct;

    @Transactional(readOnly = true)
    public CustomersDTO customerId(Long id) {
        if(id == null || id < 1) throw new IllegalArgumentException(String.format("ID cannot be lower than: %s", id));
        Customers customers = customersRepositories.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id %s not found", id)));
        return mapStruct.toDTO(customers);
    }
}
