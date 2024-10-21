package com.spring.Glassline.repository;

import com.spring.Glassline.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findBySellerIdAndId(int sellerId, int id);

    List<Product> findBySellerId(int sellerId);
}
