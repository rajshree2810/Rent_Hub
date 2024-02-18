package com.stackroute.producthistory.service;

import com.stackroute.producthistory.model.ProductHistory;
import com.stackroute.producthistory.repository.ProductHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductHistoryImpl implements ProductHistoryDAO{
    @Autowired
    ProductHistoryRepository repository;
    @Override
    public ProductHistory addTransaction(ProductHistory productHistory) {
        return repository.save(productHistory);
    }

    @Override
    public List<ProductHistory> getAllTransaction() {
        return repository.findAll();
    }

    @Override
    public List<ProductHistory> getTransactionByCustomerId(String id) {
        return repository.findByCustomerUserId(id);
    }

    @Override
    public ProductHistory getTransactionByProductId(String id) {
        return repository.findByRentedProductProductId(id);
    }

    @Override
    public List<ProductHistory> getSoldProductById(String id) {
        return repository.findByRentedProductSellerDetailsUserId(id);
    }

    @Override
    public boolean deleteOrder(String id) {
        Optional<ProductHistory> optionalProductHistory=repository.findById(id);
        if (optionalProductHistory.isPresent()){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
