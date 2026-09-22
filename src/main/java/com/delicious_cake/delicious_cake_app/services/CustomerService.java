package com.delicious_cake.delicious_cake_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.CustomerEntity;
import com.delicious_cake.delicious_cake_app.repositories.CustomerRepository;

@Service 
public class CustomerService {

    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Create Method
    public CustomerEntity create(CustomerEntity customerEntity) {

        if (customerEntity.getName() == null || customerEntity.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (customerEntity.getEmail() == null || customerEntity.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        if (customerEntity.getCc() == null || customerEntity.getCc() <= 0) {
            throw new IllegalArgumentException("Customer cc cannot be null or invalid");
        }
        return customerRepository.save(customerEntity);
    }

    //Get Method
    public CustomerEntity getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    //Get All Method
    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    //Update Method
    public CustomerEntity update(Long id, CustomerEntity customerEntity) {
        CustomerEntity existingCustomer = getById(id);

        if (customerEntity.getName() == null || customerEntity.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (customerEntity.getEmail() == null || customerEntity.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }
        if (customerEntity.getCc() == null || customerEntity.getCc() <= 0) {
            throw new IllegalArgumentException("Customer cc cannot be null or invalid");
        }

        existingCustomer.setName(customerEntity.getName());
        existingCustomer.setEmail(customerEntity.getEmail());
        existingCustomer.setCc(customerEntity.getCc());
        existingCustomer.setLastName(customerEntity.getLastName());
        return customerRepository.save(existingCustomer);
    }

    //Delete Method
    public void delete(Long id) {
        try {
            CustomerEntity existingCustomer = getById(id);
            customerRepository.delete(existingCustomer);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
