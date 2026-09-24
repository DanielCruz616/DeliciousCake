package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.SaleDetailDTO;
import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.entities.SaleDetailEntity;
import com.delicious_cake.delicious_cake_app.entities.SaleEntity;
import com.delicious_cake.delicious_cake_app.mappers.SaleDetailMapper;
import com.delicious_cake.delicious_cake_app.repositories.ProductRepository;
import com.delicious_cake.delicious_cake_app.repositories.SaleDetailRepository;
import com.delicious_cake.delicious_cake_app.repositories.SaleRepository;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleDetailService(
            SaleDetailRepository saleDetailRepository,
            SaleRepository saleRepository,
            ProductRepository productRepository) {

        this.saleDetailRepository = saleDetailRepository;
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    //Create Method
    public SaleDetailDTO create(SaleDetailDTO dto) {

        validateSaleDetail(dto);

        SaleDetailEntity saleDetail =
                SaleDetailMapper.toEntity(dto);

        SaleEntity sale = saleRepository.findById(dto.getSaleId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sale not found with id: " + dto.getSaleId()));

        ProductEntity product =
                productRepository.findById(dto.getProductId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Product not found with id: "
                                                + dto.getProductId()));

        saleDetail.setSale(sale);
        saleDetail.setProduct(product);

        SaleDetailEntity savedSaleDetail =
                saleDetailRepository.save(saleDetail);

        return SaleDetailMapper.toDTO(savedSaleDetail);
    }

    //Get Method
    public SaleDetailDTO getById(Long id) {

        SaleDetailEntity saleDetail =
                saleDetailRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Sale detail not found with id: " + id));

        return SaleDetailMapper.toDTO(saleDetail);
    }

    //Get All Method
    public List<SaleDetailDTO> getAll() {

        return saleDetailRepository.findAll()
                .stream()
                .map(SaleDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public SaleDetailDTO update(
            Long id,
            SaleDetailDTO dto) {

        SaleDetailEntity existingSaleDetail =
                saleDetailRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Sale detail not found with id: " + id));

        validateSaleDetail(dto);

        SaleEntity sale = saleRepository.findById(dto.getSaleId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sale not found with id: " + dto.getSaleId()));

        ProductEntity product =
                productRepository.findById(dto.getProductId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Product not found with id: "
                                                + dto.getProductId()));

        existingSaleDetail.setSale(sale);
        existingSaleDetail.setProduct(product);
        existingSaleDetail.setQuantity(dto.getQuantity());
        existingSaleDetail.setUnitPrice(dto.getUnitPrice());
        existingSaleDetail.setSubtotal(dto.getSubtotal());

        SaleDetailEntity updatedSaleDetail =
                saleDetailRepository.save(existingSaleDetail);

        return SaleDetailMapper.toDTO(updatedSaleDetail);
    }

    //Delete Method
    public void delete(Long id) {

        SaleDetailEntity saleDetail =
                saleDetailRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Sale detail not found with id: " + id));

        saleDetailRepository.delete(saleDetail);
    }

    //Validation Method
    private void validateSaleDetail(SaleDetailDTO dto) {

        if (dto.getSaleId() == null) {
            throw new IllegalArgumentException(
                    "Sale ID cannot be null");
        }

        if (dto.getProductId() == null) {
            throw new IllegalArgumentException(
                    "Product ID cannot be null");
        }

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero");
        }

        if (dto.getUnitPrice() == null ||
                dto.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Unit price cannot be null or negative");
        }

        if (dto.getSubtotal() == null ||
                dto.getSubtotal().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Subtotal cannot be null or negative");
        }
    }
}