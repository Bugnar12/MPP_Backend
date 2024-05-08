package org.backendspring_boot.backendspring_boot.service;

import jakarta.transaction.Transactional;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.AntivirusRepositoryJPA;
import org.backendspring_boot.backendspring_boot.repository.CustomerRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{
    private final CustomerRepositoryJPA customerRepo;
    private final AntivirusRepositoryJPA antivirusRepo;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    public CustomerServiceImpl(CustomerRepositoryJPA customerRepo, AntivirusRepositoryJPA antivirusRepo)
    {
        this.customerRepo = customerRepo;
        this.antivirusRepo = antivirusRepo;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        logger.info("Retrieved customers: " + customers);
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

    @Transactional
    public void addCustomer(Customer customer) throws RepositoryException{
        logger.info("Adding customer: " + customer);
        if(customer.validationFails()){
            logger.error("Invalid customer data: " + customer);
            throw new RepositoryException("Invalid customer data");
        }
        Long antivirusId = customer.getAntivirus().getId();
        if(antivirusRepo.findById(antivirusId).isEmpty()) {
            logger.error("Antivirus not found for customer: " + customer);
            throw new RepositoryException("Antivirus not found : Add failed");
        }
        logger.info("Saving Customer: " + customer);
        customerRepo.save(customer);
        logger.info("Customer added successfully: " + customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) throws RepositoryException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setFullName(updatedCustomer.getFullName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            customer.setAntivirus(updatedCustomer.getAntivirus());
            return customerRepo.save(customer);
        } else {
            throw new RepositoryException("Customer not found : Update failed");
        }
    }

    @Override
    public void deleteCustomer(Long id) throws RepositoryException{
        if(customerRepo.findById(id).isEmpty())
        {
            throw new RepositoryException("Customer not found : Delete failed");
        }
        customerRepo.deleteById(id);
    }

    public List<Customer> getCustomerByAntivirusId(Long antivirus_id) {
        return customerRepo.findAllByAntivirusId(antivirus_id);
    }
}