package com.stackroute.ProductAdditionSeller.repository;

import com.stackroute.ProductAdditionSeller.exception.ProductNotFoundException;
import com.stackroute.ProductAdditionSeller.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByProductId(String productId);


    List<Product> findByProductCategory(String category);

    List<Product> findByProductName(String name);

    List<Product> queryByProductNameContaining(String name);


    //List<Product> findBySellerEmail(String sellerEmail);
    //add by Rishbha M
    boolean existsByProductName(String productName);

    List<Product> queryAllByProductCategoryContainingIgnoreCase(String category);

    List<Product> findAllByProductPriceLessThan(String price);

    List<Product> queryProductByProductPriceGreaterThanEqual(String price);


//    Product findById(String productId);


}
