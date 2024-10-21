package com.spring.Glassline.repository;

import com.spring.Glassline.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItems, Integer> {

    Optional<CartItems> findByUserIdAndProductId(int userId,  int productId);

    List<CartItems> findByUserId(int userId);
}
