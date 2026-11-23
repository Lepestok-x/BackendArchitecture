package com.example.Spring.mapping;

import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.service.ServiceCustomerRead;
import com.example.Spring.service.ServiceCustomersWrite;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainMapping {
    private final ServiceCustomersWrite serviceCustomersWrite;
    private final ServiceCustomerRead serviceCustomerRead;

    @GetMapping("/customer/{id}")
    private ResponseEntity<?> requestEntity(@PathVariable Long id) {
        CustomersDTO customersDTO = serviceCustomerRead.customerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(customersDTO);
    }

    @PostMapping("/customer")
    private ResponseEntity<?> createEntity(@Validated @RequestBody CustomersDTO customersDTO) {
        serviceCustomersWrite.createCustomer(customersDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
