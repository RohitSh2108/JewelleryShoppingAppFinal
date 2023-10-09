package com.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.advices.CartException;
import com.advices.CustomerException;
import com.entity.Customer;
import com.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getallcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() throws Throwable{
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/getcustomerbyid/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws Throwable{
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createcustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Validated Customer customer) throws CustomerException{
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/updatecustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody @Validated Customer updatedCustomer) throws CustomerException{
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/deletecustomer/{userId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int userId) throws CustomerException{
        customerService.deleteCustomer(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/getcustomerbyusername/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) throws CustomerException {
        try {
            Customer customer = customerService.getCustomerByUsername(username);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
