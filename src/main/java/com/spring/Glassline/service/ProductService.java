package com.spring.Glassline.service;


import com.spring.Glassline.entity.Product;
import com.spring.Glassline.entity.User;
import com.spring.Glassline.exception.ResourceNotFoundException;
import com.spring.Glassline.repository.ProductRepository;
import com.spring.Glassline.repository.UserRepository;
import com.spring.Glassline.utils.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public ProductDto productToProductDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setCategoryId(product.getCategory());
        productDto.setType(product.getType());
        productDto.setImageSrc(product.getImageLink());
        productDto.setSpecsName(product.getSpecName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());

        return productDto;
    }


    public void addProduct(int sellerId, Product product) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller With : " + sellerId));
        Optional<Product> existingProduct = productRepository.findBySellerIdAndId(sellerId,product.getId());

        if (existingProduct.isPresent()){
          //  Product product1 = existingProduct.get();
            System.out.println(product + "updated");
            product.setSeller(seller);
            productRepository.save(product);
            System.out.println("EDIT");
            return;
        }

        product.setSeller(seller);
        productRepository.save(product);
    }

    public List<ProductDto> getAdminSpecificProductList(int sellerId) {

        List<Product> productList = productRepository.findBySellerId(sellerId);
        List<ProductDto> productDtos = productList.stream().map(this::productToProductDto)
                .collect(Collectors.toList());
        return productDtos;
    }

    public void deleteProductItemById(int sellerId, int productId) {

        Product product = productRepository.findBySellerIdAndId(sellerId, productId).orElseThrow(() -> new ResourceNotFoundException("Product With ID: " + productId));
        productRepository.delete(product);
    }

}
