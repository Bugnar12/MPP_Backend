/*
package org.example.service;

import org.backendspring_boot.backendspring_boot.BackendSpringBootApplication;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.backendspring_boot.backendspring_boot.repository.CustomerRepositoryJPA;
import org.backendspring_boot.backendspring_boot.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest(classes = BackendSpringBootApplication.class)
public class CustomerServiceTest {

    @MockBean
    private CustomerRepositoryJPA customerRepositoryJPA;

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        this.customerService = new CustomerServiceImpl(customerRepositoryJPA);
    }

    @Test
    public void testGetAllCustomers() {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Mockito.when(customerRepositoryJPA.findAll()).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertEquals(1, customers.size());
        assertEquals(customer, customers.get(0));
    }

    @Test
    public void testGetCustomerByIdSuccess() throws RepositoryException {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Mockito.when(customerRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertEquals(customer, foundCustomer);
    }

    @Test
    public void testGetCustomerByIdFails() {
        Mockito.when(customerRepositoryJPA.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RepositoryException.class, () -> customerService.getCustomerById(1L));
    }

    @Test
    public void testAddCustomer() throws RepositoryException {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Mockito.when(customerRepositoryJPA.save(customer)).thenReturn(customer);

        customerService.addCustomer(customer);

        Mockito.verify(customerRepositoryJPA, Mockito.times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomer() throws RepositoryException {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Customer updatedCustomer = new Customer("updated", "updated@test.com", 35, antivirus);
        updatedCustomer.setId(1L);

        Mockito.when(customerRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepositoryJPA.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        assertEquals(updatedCustomer, result);
    }

    @Test
    public void testDeleteCustomer() throws RepositoryException {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Mockito.when(customerRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(1L);

        Mockito.verify(customerRepositoryJPA, Mockito.times(1)).findById(1L);
        Mockito.verify(customerRepositoryJPA, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testGetCustomerByAntivirusId() {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        antivirus.setId(1L);

        Customer customer = new Customer("test", "test@test.com", 30, antivirus);
        customer.setId(1L);

        Mockito.when(customerRepositoryJPA.findAllByAntivirusId(anyLong())).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.getCustomerByAntivirusId(antivirus.getId());

        assertEquals(1, customers.size());
        assertEquals(customer, customers.get(0));
    }
}*/
