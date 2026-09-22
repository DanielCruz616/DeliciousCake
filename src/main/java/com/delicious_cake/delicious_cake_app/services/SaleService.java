package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;

import com.delicious_cake.delicious_cake_app.entities.SaleEntity;
import com.delicious_cake.delicious_cake_app.repositories.SaleRepository;

public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    //Create Method
    public SaleEntity createSale(SaleEntity sale) {
        if (sale.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (sale.getTable() == null) {
            throw new IllegalArgumentException("Table cannot be null");
        }
        if (sale.getTotal() == null || sale.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total cannot be null or negative");
        }
        return saleRepository.save(sale);
    }

    //Get Method
    public SaleEntity getSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found with id: " + id));
    }

    //Get All Method
    public List<SaleEntity> getAllSales() {
        return saleRepository.findAll();
    }

    //Update Method
    public SaleEntity updateSale(Long id, SaleEntity sale) {
        SaleEntity existingSale = getSaleById(id);

        if (sale.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (sale.getTable() == null) {
            throw new IllegalArgumentException("Table cannot be null");
        }
        if (sale.getTotal() == null || sale.getTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total cannot be null or negative");
        }

        existingSale.setCustomer(sale.getCustomer());
        existingSale.setTable(sale.getTable());
        existingSale.setTotal(sale.getTotal());

        return saleRepository.save(existingSale);
    }
    
    //Delete Method
    public void deleteSale(Long id) {
        try {
            SaleEntity existingSale = getSaleById(id);
            saleRepository.delete(existingSale);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sale not found with id: " + id);
        }
    }
}
