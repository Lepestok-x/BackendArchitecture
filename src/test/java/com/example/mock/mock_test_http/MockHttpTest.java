package com.example.mock.mock_test_http;

import com.example.Spring.dto.CustomersDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockHttpTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Test
    void testCustomersDTOValidate() throws Exception {
        CustomersDTO customersDTO = new CustomersDTO("", "", "", "", "");
        assertFalse(validator.validate(customersDTO).isEmpty());
    }
}
