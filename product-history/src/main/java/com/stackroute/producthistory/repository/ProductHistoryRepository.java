package com.stackroute.producthistory.repository;

import com.stackroute.producthistory.model.ProductHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHistoryRepository extends MongoRepository<ProductHistory,String> {
    public List<ProductHistory> findByCustomerUserId(String customer_userId);
    public ProductHistory findByRentedProductProductId(String id);
    public List<ProductHistory> findByRentedProductSellerDetailsUserId(String id);
}
