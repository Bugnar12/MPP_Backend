package org.backendspring_boot.backendspring_boot;

import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.entity.CustomerMockFaker;
import org.backendspring_boot.backendspring_boot.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final CustomerServiceImpl customerService;
    private final CustomerMockFaker customerMockFaker;

    @Autowired
    public DataInitializer(CustomerServiceImpl customerService, CustomerMockFaker customerMockFaker) {
        this.customerService = customerService;
        this.customerMockFaker = customerMockFaker;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
        List<Customer> existingCustomers = customerService.getAllCustomers();
        for (Customer customer : existingCustomers) {
            customerService.deleteCustomer(customer.getId());
        }

        // Generate and save new data
        List<Customer> customers = customerMockFaker.generateRandomCustomers();
        for (Customer customer : customers) {
            customerService.addCustomer(customer);
        }
    }
}