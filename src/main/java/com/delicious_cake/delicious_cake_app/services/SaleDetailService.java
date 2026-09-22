package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.SaleDetailEntity;
import com.delicious_cake.delicious_cake_app.repositories.SaleDetailRepository;

@Service 
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    //Create Method
    public SaleDetailEntity createSaleDetail(SaleDetailEntity saleDetail) {
        if (saleDetail.getSale() == null) {
            throw new IllegalArgumentException("Sale cannot be null");
        }
        if (saleDetail.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (saleDetail.getQuantity() == null || saleDetail.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (saleDetail.getUnitPrice() == null || saleDetail.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be null or negative");
        }
        if (saleDetail.getSubtotal() == null || saleDetail.getSubtotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Subtotal cannot be null or negative");
        }
        return saleDetailRepository.save(saleDetail);
    }

    //Get Method
    public SaleDetailEntity getSaleDetailById(Long id) {
        return saleDetailRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale detail not found with id: " + id));
    }

    //Get All Method
    public List<SaleDetailEntity> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }  

    //Update Method
    public SaleDetailEntity updateSaleDetail(Long id, SaleDetailEntity saleDetail) {
        SaleDetailEntity existingSaleDetail = getSaleDetailById(id);

        if (saleDetail.getSale() == null) {
            throw new IllegalArgumentException("Sale cannot be null");
        }
        if (saleDetail.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (saleDetail.getQuantity() == null || saleDetail.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (saleDetail.getUnitPrice() == null || saleDetail.getUnitPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be null or negative");
        }
        if (saleDetail.getSubtotal() == null || saleDetail.getSubtotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Subtotal cannot be null or negative");
        }

        existingSaleDetail.setSale(saleDetail.getSale());
        existingSaleDetail.setProduct(saleDetail.getProduct());
        existingSaleDetail.setQuantity(saleDetail.getQuantity());
        existingSaleDetail.setUnitPrice(saleDetail.getUnitPrice());
        existingSaleDetail.setSubtotal(saleDetail.getSubtotal());

        return saleDetailRepository.save(existingSaleDetail);
    }

    //Delete Method
    public void deleteSaleDetail(Long id) {
        try {
            SaleDetailEntity existingSaleDetail = getSaleDetailById(id);
            saleDetailRepository.delete(existingSaleDetail);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sale detail not found with id: " + id);
        }
    }
}
