package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.CustomerRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{
    private final CustomerRepositoryJPA customerRepo;

    public CustomerServiceImpl(CustomerRepositoryJPA customerRepo)
    {
        this.customerRepo = customerRepo;
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        System.out.println("Retrieved customers: " + customers);
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) throws RepositoryException{
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isEmpty())
        {
            throw new RepositoryException("Customer not found : Get by id failed");
        }

        return customer.get();
    }

    public void addCustomer(Customer customer) {
        if(customer.validationFails()) {
            System.out.println("Validation failed for Customer: " + customer);
            return;
        }
        if(customer.getAntivirus() == null) {
            customer.setAntivirus(null);
        }
        System.out.println("Saving Customer: " + customer);
        customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isPresent()) {
            System.out.println("avem la ce da update");
            Customer customer = customerOptional.get();
            customer.setFullName(updatedCustomer.getFullName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            customer.setAntivirus(updatedCustomer.getAntivirus());
            System.out.println(customer);
            return customerRepo.save(customer);
        }
        System.out.println("nu avem la ce da update");
        return null;
    }

    @Override
    public void deleteCustomer(Long id) throws RepositoryException{
        if(customerRepo.findById(id).isEmpty())
        {
            throw new RepositoryException("Customer not found : Delete failed");
        }
    }

    public List<Customer> getCustomerByAntivirusId(Long antivirus_id) {
        return customerRepo.findAllByAntivirusId(antivirus_id);
    }
}
