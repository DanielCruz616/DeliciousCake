package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.SaleDTO;
import com.delicious_cake.delicious_cake_app.entities.CustomerEntity;
import com.delicious_cake.delicious_cake_app.entities.SaleEntity;
import com.delicious_cake.delicious_cake_app.entities.StandEntity;
import com.delicious_cake.delicious_cake_app.mappers.SaleMapper;
import com.delicious_cake.delicious_cake_app.repositories.CustomerRepository;
import com.delicious_cake.delicious_cake_app.repositories.SaleRepository;
import com.delicious_cake.delicious_cake_app.repositories.StandRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final StandRepository standRepository;

    public SaleService(
            SaleRepository saleRepository,
            CustomerRepository customerRepository,
            StandRepository standRepository) {

        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.standRepository = standRepository;
    }

    //Create Method finding the customer and stand by their IDs
    public SaleDTO create(SaleDTO dto) {

        validateSale(dto);

        SaleEntity sale = SaleMapper.toEntity(dto);

        CustomerEntity customer =
                customerRepository.findById(dto.getCustomerId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Customer not found with id: "
                                                + dto.getCustomerId()));

        StandEntity stand =
                standRepository.findById(dto.getTableId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Stand not found with id: "
                                                + dto.getTableId()));

        sale.setCustomer(customer);
        sale.setTable(stand);

        SaleEntity savedSale = saleRepository.save(sale);

        return SaleMapper.toDTO(savedSale);
    }

    //Get Method
    public SaleDTO getById(Long id) {

        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sale not found with id: " + id));

        return SaleMapper.toDTO(sale);
    }

    //Get All Method
    public List<SaleDTO> getAll() {

        return saleRepository.findAll()
                .stream()
                .map(SaleMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method finding the customer and stand by their IDs
    public SaleDTO update(Long id, SaleDTO dto) {
        SaleEntity existingSale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sale not found with id: " + id));

        validateSale(dto);

        CustomerEntity customer =
                customerRepository.findById(dto.getCustomerId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Customer not found with id: "
                                                + dto.getCustomerId()));

        StandEntity stand =
                standRepository.findById(dto.getTableId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Stand not found with id: "
                                                + dto.getTableId()));

        existingSale.setCustomer(customer);
        existingSale.setTable(stand);
        existingSale.setTotal(dto.getTotal());

        SaleEntity updatedSale = saleRepository.save(existingSale);

        return SaleMapper.toDTO(updatedSale);
    }

    //Delete Method
    public void delete(Long id) {

        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sale not found with id: " + id));

        saleRepository.delete(sale);
    }

    //Validation Method
    private void validateSale(SaleDTO dto) {

        if (dto.getCustomerId() == null) {
            throw new IllegalArgumentException(
                    "Customer ID cannot be null");
        }

        if (dto.getTableId() == null) {
            throw new IllegalArgumentException(
                    "Table ID cannot be null");
        }

        if (dto.getTotal() == null ||
                dto.getTotal().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Total cannot be null or negative");
        }
    }
}