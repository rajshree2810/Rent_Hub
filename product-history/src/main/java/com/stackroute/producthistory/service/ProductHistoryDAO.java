package com.stackroute.producthistory.service;

import com.stackroute.producthistory.model.ProductHistory;

import java.util.List;

public interface ProductHistoryDAO {
    public ProductHistory addTransaction(ProductHistory productHistory);

    public List<ProductHistory> getAllTransaction();

    public List<ProductHistory> getTransactionByCustomerId(String id);

    public ProductHistory getTransactionByProductId(String id);

    public List<ProductHistory> getSoldProductById(String id);

    public boolean deleteOrder(String id);


}
