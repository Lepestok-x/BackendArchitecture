package com.example.mock;
import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mapper.MapStruct;
import com.example.Spring.repositories.CustomersRepositories;
import com.example.Spring.service.ServiceCustomerRead;
import com.example.Spring.service.ServiceCustomersWrite;
import com.example.mock.faker.FakerData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MockAnnotationTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @InjectMocks
    private ServiceCustomerRead serviceCustomerRead;
    @InjectMocks
    private ServiceCustomersWrite customersWrite;
    @InjectMocks
    private FakerData fakerData;
    @Mock
    private CustomersRepositories customersRepositories;
    @Mock
    private MapStruct mapStruct;
    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, () -> serviceCustomerRead.customerId(0L));
        assertThrows(IllegalArgumentException.class, () -> serviceCustomerRead.customerId(-85L));
        assertThrows(IllegalArgumentException.class, () -> serviceCustomerRead.customerId(null));
        verifyNoMoreInteractions(customersRepositories);
    }
    @Test
    void testThrowReadCustomersException() {
        when(customersRepositories.findById(99L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> serviceCustomerRead.customerId(99L));
    }
    @Test
    void testWriteDataCustomer() {
        CustomersDTO customersDTO = new CustomersDTO(fakerData.contactName(), fakerData.address(), fakerData.city(), fakerData.postalCode(),  fakerData.country());
        Customers customers = new Customers(customersDTO);
        when(mapStruct.toEntity(customersDTO)).thenReturn(customers);
        customersWrite.createCustomer(customersDTO);

        verify(mapStruct).toEntity(customersDTO);
        verify(customersRepositories).save(customers);
    }

}
