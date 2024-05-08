package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id) throws RepositoryException;
    void addCustomer(Customer customer) throws RepositoryException;
    Customer updateCustomer(Long id, Customer updatedCustomer) throws RepositoryException;
    void deleteCustomer(Long id) throws RepositoryException;
}
