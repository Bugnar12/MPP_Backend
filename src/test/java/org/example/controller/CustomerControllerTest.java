package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backendspring_boot.backendspring_boot.BackendSpringBootApplication;
import org.backendspring_boot.backendspring_boot.controller.CustomerController;
import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.service.CustomerServiceImpl;
import org.backendspring_boot.backendspring_boot.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@ContextConfiguration(classes= BackendSpringBootApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer("test", "test@test.com", 25);
        customer.setId(1L);

        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));

        mockMvc.perform(get("/customerList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName", is("test")));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer("test", "test@test.com", 25);
        customer.setId(1L);

        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);

        mockMvc.perform(get("/customerList/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("test")));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testUpdateCustomer() throws Exception {
        Antivirus antivirus = new Antivirus("test", "test", "test", true, new java.sql.Date(System.currentTimeMillis()));
        Customer customer = new Customer("test", "test@test.com", 25);
        customer.setId(1L);
        customer.setAntivirus(antivirus);

        when(customerService.updateCustomer(eq(customer.getId()), any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/customerList/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());


/*        Customer capturedCustomer = customerCaptor.getValue();
        assertEquals(customer.getId(), capturedCustomer.getId());
        assertEquals(customer.getFullName(), capturedCustomer.getFullName());
        assertEquals(customer.getEmail(), capturedCustomer.getEmail());
        assertEquals(customer.getAge(), capturedCustomer.getAge());*/
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER", "ADMIN"})
    public void testDeleteCustomer() throws Exception {
        Long customerId = 1L;

        doNothing().when(customerService).deleteCustomer(customerId);

        mockMvc.perform(delete("/customerList/" + customerId))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}