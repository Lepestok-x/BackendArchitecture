package com.example.Spring.service;
import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mapper.MapStruct;
import com.example.Spring.repositories.CustomersRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ServiceCustomersWrite {
    private final CustomersRepositories customersRepositories;
    private final MapStruct mapStruct;

    @Transactional(rollbackFor = Exception.class)
    public void createCustomer(@Validated CustomersDTO customersDTO) {
        customersRepositories.save(mapStruct.toEntity(customersDTO));
    }
}
