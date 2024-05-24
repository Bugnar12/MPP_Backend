package org.backendspring_boot.backendspring_boot.entity;

import com.github.javafaker.Faker;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CustomerMockFaker {
    private final AntivirusServiceImpl antivirusService;

    @Autowired
    public CustomerMockFaker(AntivirusServiceImpl antivirusService) {
        this.antivirusService = antivirusService;
    }

    public List<Customer> generateRandomCustomers() {
        int numberOfCustomers = 100000;
        Faker faker = new Faker();
        Random random = new Random();
        List<Antivirus> antiviruses = antivirusService.getAllAntivirus();
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < numberOfCustomers; i++) {
            String fullName = faker.name().fullName();
            String email = faker.internet().emailAddress();
            int age = 18 + random.nextInt(20);
            Antivirus antivirus = antiviruses.get(random.nextInt(antiviruses.size())); // Random antivirus

            Customer customer = new Customer(fullName, email, age, antivirus);
            customers.add(customer);
        }

        return customers;
    }
}