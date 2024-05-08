package org.backendspring_boot.backendspring_boot.controller;

import jakarta.validation.Valid;
import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService)
    {
        this.customerService = customerService;
    }
    @GetMapping("/customerList")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/getCustomerByAntivirusId/{antivirus_id}")
    public ResponseEntity<List<Customer>> getCustomerByAntivirusId(@PathVariable Long antivirus_id){
        List<Customer> customers = customerService.getCustomerByAntivirusId(antivirus_id);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("customerList/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customerList")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer){
        if(customer.validationFails())
        {
            return ResponseEntity.badRequest().build();
        }
        try {
            customerService.addCustomer(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/customerList/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody @Valid Customer updatedCustomer){
        if(updatedCustomer.validationFails())
        {
            return ResponseEntity.badRequest().build();
        }
        try {
            Customer customer = customerService.updateCustomer(id, updatedCustomer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/customerList/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
