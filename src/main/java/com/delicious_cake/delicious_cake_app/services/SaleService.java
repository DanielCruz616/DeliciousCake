package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.SaleDTO;
import com.delicious_cake.delicious_cake_app.dtos.SaleDetailDTO;
import com.delicious_cake.delicious_cake_app.entities.CustomerEntity;
import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.entities.SaleEntity;
import com.delicious_cake.delicious_cake_app.entities.StandEntity;
import com.delicious_cake.delicious_cake_app.mappers.SaleMapper;
import com.delicious_cake.delicious_cake_app.repositories.CustomerRepository;
import com.delicious_cake.delicious_cake_app.repositories.ProductRepository;
import com.delicious_cake.delicious_cake_app.repositories.SaleRepository;
import com.delicious_cake.delicious_cake_app.repositories.StandRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final StandRepository standRepository;
    private final SaleDetailService saleDetailService;
    private final ProductRepository productRepository;

    public SaleService(
            SaleRepository saleRepository,
            CustomerRepository customerRepository,
            StandRepository standRepository,
            ProductRepository productRepository,
            SaleDetailService saleDetailService) {

        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.standRepository = standRepository;
        this.productRepository = productRepository;
        this.saleDetailService = saleDetailService
        ;
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
        sale.setCreatedAt(LocalDate.now());

        BigDecimal total = BigDecimal.ZERO;

        for (SaleDetailDTO detailDTO : dto.getDetails()) {

                ProductEntity product = productRepository.findById(detailDTO.getProductId())
                                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + detailDTO.getProductId()));

                BigDecimal unitPrice = product.getPrice();

                BigDecimal subtotal = unitPrice.multiply(
                        BigDecimal.valueOf(detailDTO.getQuantity())
                );

                total = total.add(subtotal);
        }

        sale.setTotal(total);
        
        SaleEntity savedSale = saleRepository.save(sale);

        for (SaleDetailDTO detailDTO : dto.getDetails()) {

                detailDTO.setSaleId(savedSale.getId());
                saleDetailService.create(detailDTO);
        }

        savedSale.setTotal(total);

        saleRepository.save(savedSale);

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
    }
}