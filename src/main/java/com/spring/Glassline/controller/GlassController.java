package com.spring.Glassline.controller;


import com.spring.Glassline.entity.Glass;
import com.spring.Glassline.repository.GlassRepository;
import com.spring.Glassline.repository.ProductRepository;
import com.spring.Glassline.service.ProductService;
import com.spring.Glassline.utils.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GlassController {

    @Autowired
    private GlassRepository glassRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    @GetMapping("/data")
    public ResponseEntity<List<Glass>> getAllGlasses(){
        return new ResponseEntity<>(glassRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProduct(){

        List<ProductDto> productDtos = productRepository.findAll()
                .stream().map(product -> productService.productToProductDto(product))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }


}
