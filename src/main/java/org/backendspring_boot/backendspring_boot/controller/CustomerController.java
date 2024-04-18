package org.backendspring_boot.backendspring_boot.controller;

import org.backendspring_boot.backendspring_boot.entity.Customer;
import org.backendspring_boot.backendspring_boot.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
