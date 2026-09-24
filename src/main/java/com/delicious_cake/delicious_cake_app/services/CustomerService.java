package com.delicious_cake.delicious_cake_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.CustomerDTO;
import com.delicious_cake.delicious_cake_app.entities.CustomerEntity;
import com.delicious_cake.delicious_cake_app.mappers.CustomerMapper;
import com.delicious_cake.delicious_cake_app.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Create Method
    public CustomerDTO create(CustomerDTO dto) {

        validateCustomer(dto);

        CustomerEntity customer = CustomerMapper.toEntity(dto);
        CustomerEntity savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDTO(savedCustomer);
    }

    //Get Method
    public CustomerDTO getById(Long id) {

        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found with id: " + id));

        return CustomerMapper.toDTO(customer);
    }

    //Get All Method
    public List<CustomerDTO> getAll() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public CustomerDTO update(Long id, CustomerDTO dto) {

        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found with id: " + id));

        validateCustomer(dto);

        existingCustomer.setName(dto.getName());
        existingCustomer.setEmail(dto.getEmail());
        existingCustomer.setCc(dto.getCc());
        existingCustomer.setLastName(dto.getLastName());

        CustomerEntity updatedCustomer = customerRepository.save(existingCustomer);

        return CustomerMapper.toDTO(updatedCustomer);
    }

    //Delete Method
    public void delete(Long id) {

        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found with id: " + id));

        customerRepository.delete(customer);
    }

    //Validation Method
    private void validateCustomer(CustomerDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }

        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Customer email cannot be null or empty");
        }

        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Customer last name cannot be null or empty");
        }

        if (dto.getCc() == null || dto.getCc() <= 0) {
            throw new IllegalArgumentException("Customer cc cannot be null or invalid");
        }
    }
}