package com.example.mock.faker;

import net.datafaker.Faker;

public class FakerData {
    Faker faker = new Faker();

    public String contactName() {
        return faker.name().fullName();
    }
    public String address() {
        return faker.address().fullAddress();
    }
    public String city() {
        return faker.address().city();
    }
    public String postalCode() {
        return faker.address().postcode();
    }
    public String country() {
        return faker.address().country();
    }
}
