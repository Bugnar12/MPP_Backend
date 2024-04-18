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
        return customerRepo.findAll();
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

    @Override
    public void addCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer updatedCustomer) throws RepositoryException{
        if(customerRepo.findById(updatedCustomer.getId()).isEmpty())
        {
            throw new RepositoryException("Customer not found : Update failed");
        }

        Customer customer = customerRepo.findById(id).orElse(null);

        if(customer != null)
        {
            customer.setFullName(updatedCustomer.getFullName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
        }
        customerRepo.save(customer);
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
