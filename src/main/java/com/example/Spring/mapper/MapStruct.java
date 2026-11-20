package com.example.Spring.mapper;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapStruct {
    CustomersDTO toDTO(Customers customers);
    Customers toEntity(CustomersDTO customersDTO);
}
