package com.serviceImpl;

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
import com.service.CustomerService;

//import jakarta.validation.Valid;

@Service
@Validated
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @Override
    public Optional<Customer> getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Customer createCustomer(@Validated Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        // You don't need to set the customerId; it's inherited from AppUser.
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }
    
    @Override
    public Customer getCustomerByUsername(String username) throws CustomerException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (!customer.isPresent()) {
            throw new CustomerException("Customer not found with username: " + username);
        }
        return customer.get();
    }
}
