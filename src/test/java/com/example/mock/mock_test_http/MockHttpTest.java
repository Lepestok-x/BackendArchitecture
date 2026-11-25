package com.example.mock.mock_test_http;
import com.example.Spring.Application;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.controller.CustomerController;
import com.example.Spring.exception.AppException;
import com.example.Spring.service.ServiceCustomerRead;
import com.example.Spring.service.ServiceCustomersWrite;
import com.example.mock.faker.FakerData;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CustomerController.class)
@ContextConfiguration(classes = {Application.class, CustomerController.class})
public class MockHttpTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private FakerData fakerData;
    @MockitoBean
    private ServiceCustomersWrite serviceCustomersWrite;
    @MockitoBean
    private ServiceCustomerRead serviceCustomerRead;
    @Test
    void testCustomersDTOValidate() throws Exception {
        CustomersDTO customersDTO = new CustomersDTO("", "", "", "", "");
        assertFalse(validator.validate(customersDTO).isEmpty());
    }

    @Test
    void testCreatePost() throws Exception {
        String contact = fakerData.contactName();
        CustomersDTO customersDTO = new CustomersDTO(contact, fakerData.address(), fakerData.address(), fakerData.postalCode(), fakerData.country());
        mockMvc.perform(post("/main/customer")
            .with(csrf())
            .contentType("application/json")
            .content(objectMapper.writeValueAsBytes(customersDTO)))
            .andExpect(status().isCreated());
    }
    @Test
    void testGetCustomerById() throws Exception {
        String contact = fakerData.contactName();
        CustomersDTO customersDTO = new CustomersDTO(contact, fakerData.address(), fakerData.address(), fakerData.postalCode(), fakerData.country());
        when(serviceCustomerRead.customerId(1L)).thenReturn(customersDTO);

        mockMvc.perform(get("/main/customer/{id}", 1L)
            .content("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.contactName").value(contact));
    }
    @Test
    void testGetCustomerByIdNotFound() throws Exception {
        when(serviceCustomerRead.customerId(-1L)).thenThrow(new AppException("ID cannot be lower than:", HttpStatus.BAD_REQUEST));
        assertThrows(AppException.class, () -> serviceCustomerRead.customerId(-1L));

        mockMvc.perform(get("/main/customer/{id}", -1L)
            .with(csrf())
            .content("application/json"))
            .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.toString()));
    }
}
