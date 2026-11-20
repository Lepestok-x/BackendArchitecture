package com.example.Spring.database;

import com.example.Spring.dto.CustomersDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contactname")
    private String contactName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "postalcode")
    private String postalCode;
    @Column(name = "country")
    private String country;

    public Customers(CustomersDTO customersDTO) {
        this.contactName = customersDTO.contactName();
        this.address = customersDTO.address();
        this.city = customersDTO.city();
        this.postalCode = customersDTO.postalCode();
        this.country = customersDTO.country();
    }
    public Customers() {}
}
