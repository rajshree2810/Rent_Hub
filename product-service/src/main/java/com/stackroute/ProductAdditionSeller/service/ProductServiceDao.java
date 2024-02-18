package com.stackroute.ProductAdditionSeller.service;

import com.stackroute.ProductAdditionSeller.exception.ProductNotFoundException;
import com.stackroute.ProductAdditionSeller.model.Product;

import java.util.List;

public interface ProductServiceDao {

    public Product addProduct(Product product) ;

    List<Product> getAllProduct();

    Product getProductById(String productId)throws ProductNotFoundException;

    List<Product> getProductsByCategory(String productCategory) throws ProductNotFoundException;

    List<Product> getProductsByName(String productName)throws ProductNotFoundException;

//    String deleteProductById(String productId);

//    List<Product> getProductBySellerEmail(String sellerEmail);

//    Product updateProduct(Product product);

    //add by Risabh
    boolean existsByProductName(String productName);
    //void rishabh added
    List<Product> findByProductName(String productName);

    List<String> findAllCategories();

    List<Product> filterByProductCategory(String category);

    List<Product> filterByAmountLessthan(String price);

    List<Product> filterByAmountMorethan(String price);

}
