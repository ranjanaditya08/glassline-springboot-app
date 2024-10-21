package com.spring.Glassline.controller;


import com.spring.Glassline.entity.Product;
import com.spring.Glassline.service.ProductService;
import com.spring.Glassline.utils.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{sellerId}")
    public ResponseEntity<Void> addProduct(@PathVariable int sellerId, @RequestBody Product product){
        productService.addProduct(sellerId,product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{sellerId}/{productId}")
    public ResponseEntity<Void> deleteProductItemById(@PathVariable int sellerId, @PathVariable int productId){
        productService.deleteProductItemById(sellerId,productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<List<ProductDto>> findAdminSpecificProductList(@PathVariable int sellerId){
        List<ProductDto> productList = productService.getAdminSpecificProductList(sellerId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
