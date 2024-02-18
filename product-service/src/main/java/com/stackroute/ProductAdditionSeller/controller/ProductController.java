package com.stackroute.ProductAdditionSeller.controller;

import com.stackroute.ProductAdditionSeller.exception.ProductNotFoundException;
import com.stackroute.ProductAdditionSeller.model.Product;
import com.stackroute.ProductAdditionSeller.service.ProductServiceDao;
import com.stackroute.ProductAdditionSeller.service.SellerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

//import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//@Slf4j
@RestController
@RequestMapping("product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductServiceDao service;

    @Autowired
    SellerFeign sellerFeign;

    @PostMapping("/addProduct/{userId}")
    public ResponseEntity<?> addProduct(@RequestBody Product product,@PathVariable("userId") String userId){
        System.out.println(userId);
        product.setSellerDetails(sellerFeign.getSellerDetailById(userId));
        System.out.println(product.getSellerDetails().getFirstName());
        return new ResponseEntity<>(service.addProduct(product) , HttpStatus.CREATED) ;
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllProduct(){
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }
//
//    @PostMapping(value = "/addproduct") // localhost:8082/product/addproduct
//    public ResponseEntity<Product> addProduct(@RequestParam("product") String product,
//                                              @RequestParam("file") MultipartFile image) throws IOException {
//
//
//        ObjectMapper objectMapper=new ObjectMapper();
//        Product product1 =objectMapper.readValue(product,Product.class);
//        product1.setProductImage(image.getBytes());
//
//        return new ResponseEntity<>(service.addProduct(product1), HttpStatus.CREATED);
//
//
//    }
//
//    @GetMapping(value = "/products") // localhost:8082/product/products
//    public ResponseEntity<List<Product>> getAllProduct() {
//        return new ResponseEntity<List<Product>>(service.getAllProduct(), HttpStatus.OK);
//    }
//
    @GetMapping(value = "/{productId}") // localhost:8082/product/product/{productId}
    public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) {
        try {
            return new ResponseEntity<>(service.getProductById(productId),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = service.getProductsByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductsByName(@PathVariable("name") String name) {
        try {
            List<Product> products = service.getProductsByName(name);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }



//
//    @GetMapping(value = "/getbyemail/{sellerEmail}") // localhost:8082/product/getbyemail/{sellerEmail}
//    public ResponseEntity<List<Product>> getProductBySellerEmail(@PathVariable String sellerEmail) {
//        return new ResponseEntity<List<Product>>(service.getProductBySellerEmail(sellerEmail), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/deleteproduct/{productId}") // localhost:8082/product/deleteproduct/{productId}
//    public ResponseEntity<String> deleteProductById(@PathVariable String productId) {
//        return new ResponseEntity<String>(service.deleteProductById(productId), HttpStatus.OK);
//    }
//
//    @PutMapping(value = "/updateproduct") // localhost:8092/product/updateproduct
//    public ResponseEntity<Product> updateProductById(@RequestBody Product product) {
//        return new ResponseEntity<Product>(service.updateProductById(product), HttpStatus.OK);
//    }


    //add by Rishabh M
    @GetMapping("/exists/{productName}")
    public ResponseEntity<Map<String, Boolean>> checkProductExists(@PathVariable String productName) {
        boolean exists = service.existsByProductName(productName);
        Map<String, Boolean> response = Collections.singletonMap("exists", exists);
        return ResponseEntity.ok(response);
    }

    //add by void rishbha
    @GetMapping("/search/{productName}")
    public ResponseEntity<List<Product>> getRecommendedProductsByName(@PathVariable String productName) {
        List<Product> products = service.findByProductName(productName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("listcategories")
    public ResponseEntity<?> getAllCategory(){
        List<String> categories=service.findAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("filterCategory")
    public ResponseEntity<?> filterByProductCategory(@RequestHeader("categoryheader") String category){
        System.out.println("Category is "+category);
        return new ResponseEntity<>(service.filterByProductCategory(category),HttpStatus.OK);
    }

    @GetMapping("filterbyamountlessthan/{price}")
    public ResponseEntity<?> filterByAmountLessThan(@PathVariable("price")String price){
        return new ResponseEntity<>(service.filterByAmountLessthan(price),HttpStatus.OK);
    }

    @GetMapping("filterbyamountmorethan/{price}")
    public ResponseEntity<?> filterByAmountMoreThan(@PathVariable("price")String price){
        return new ResponseEntity<>(service.filterByAmountMorethan(price),HttpStatus.OK);
    }

}
