package com.stackroute.ProductAdditionSeller.service;

//import com.stackroute.ProductAdditionSeller.config.Producer;
//import com.stackroute.ProductAdditionSeller.config.ProducerDTO;
import com.stackroute.ProductAdditionSeller.exception.IdNotFoundException;
import com.stackroute.ProductAdditionSeller.model.Product;
import com.stackroute.ProductAdditionSeller.repository.ProductRepository;
import com.stackroute.ProductAdditionSeller.service.ProductServiceDao;
import com.stackroute.ProductAdditionSeller.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductServiceDao{
    @Autowired
    ProductRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(String productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct=repository.findById(productId);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }else {
            throw new ProductNotFoundException("Product with this id is not found");
        }
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws ProductNotFoundException {
        List<Product> products = repository.findByProductCategory(category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products with this category are not found");
        }
        return products;
    }

    @Override
    public List<Product> getProductsByName(String name) throws ProductNotFoundException {
        List<Product> products = repository.queryByProductNameContaining(name);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products with this name are not found");
        }
        return products;
    }



    //add by RM
    @Override
    public boolean existsByProductName(String productName) {
        return repository.existsByProductName(productName);
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return repository.findByProductName(productName);
    }


    @Override
    public List<String> findAllCategories() {
        List<String> category = mongoTemplate.query(Product.class).distinct("productCategory").as(String.class).all();
        return category;
    }

    @Override
    public List<Product> filterByProductCategory(String category) {
        return repository.queryAllByProductCategoryContainingIgnoreCase(category.split(" ")[0]);
    }

    @Override
    public List<Product> filterByAmountLessthan(String price) {
        return repository.findAllByProductPriceLessThan(price);
    }

    @Override
    public List<Product> filterByAmountMorethan(String price) {
        return repository.queryProductByProductPriceGreaterThanEqual(price);
    }
}
