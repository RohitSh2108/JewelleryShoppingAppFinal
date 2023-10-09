package com.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.advices.CustomerException;
import com.entity.Customer;
import com.repository.CustomerRepository;
//import jakarta.validation.Valid;


public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Optional<Customer> getCustomerById(int customerId) ;
    public Customer createCustomer(@Validated Customer customer) ;
    public Customer updateCustomer(int customerId, Customer updatedCustomer) ;
    public void deleteCustomer(int customerId) ;
    Customer getCustomerByUsername(String username) throws CustomerException;   
}

