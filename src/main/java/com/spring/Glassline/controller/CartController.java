package com.spring.Glassline.controller;


import com.spring.Glassline.entity.CartItems;
import com.spring.Glassline.service.CartService;
import com.spring.Glassline.utils.CartRequest;
import com.spring.Glassline.utils.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartResponse>> getUserSpecificCartItem(@PathVariable int userId){
        return new ResponseEntity<>(cartService.getUserSpecificCartItem(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addItemSToCart(@RequestBody CartRequest cartRequest){
        cartService.addItemsToCat(cartRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}/{productId}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable int userId, @PathVariable int productId){
        cartService.deleteCartItemById(userId,productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
