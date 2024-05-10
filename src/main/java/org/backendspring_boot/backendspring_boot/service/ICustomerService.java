package org.backendspring_boot.backendspring_boot.service;

import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id) throws RepositoryException;
    void addCustomer(Customer customer) throws RepositoryException;
    Customer updateCustomer(Long id, Customer updatedCustomer) throws RepositoryException;
    void deleteCustomer(Long id) throws RepositoryException;
    List<Customer> getCustomerByAntivirusId(Long antivirus_id);
    Page<Customer> getCustomerByAntivirusId(Long antivirus_id, int page, int size) throws RepositoryException;
    int noOfCustomersByAntivirus(Long id) throws RepositoryException;
}
