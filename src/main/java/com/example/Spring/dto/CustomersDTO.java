package com.example.Spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record CustomersDTO(
    @JsonProperty("contactName")
    @NotEmpty(message = "Contact name not is empty")
    String contactName,
    @JsonProperty("address")
    @NotEmpty(message = "Address name not is empty")
    String address,
    @JsonProperty("city")
    @NotEmpty(message = "City not is empty")
    String city,
    @JsonProperty("code")
    @NotEmpty(message = "Postal code not is empty")
    String postalCode,
    @JsonProperty("country")
    @NotEmpty(message = "Country not is empty")
    String country
)
{
}
