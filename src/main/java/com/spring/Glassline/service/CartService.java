package com.spring.Glassline.service;

import com.spring.Glassline.entity.CartItems;
import com.spring.Glassline.entity.Glass;
import com.spring.Glassline.entity.User;
import com.spring.Glassline.exception.ResourceNotFoundException;
import com.spring.Glassline.repository.CartRepository;
import com.spring.Glassline.repository.GlassRepository;
import com.spring.Glassline.repository.UserRepository;
import com.spring.Glassline.utils.CartRequest;
import com.spring.Glassline.utils.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GlassRepository glassRepository;

    @Autowired
    private CartRepository cartRepository;

    public void addItemsToCat(CartRequest cartRequest) {
        int userId = cartRequest.getUserId();
        int productId = cartRequest.getProductId();
        int quantity = cartRequest.getQuantity();

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with "+userId));
        Optional<CartItems> existingCartItem = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {

            CartItems item = existingCartItem.get();
            item.setQuantity(quantity);
            cartRepository.save(item);
        } else {

            CartItems newItem = new CartItems();
            newItem.setUser(user);
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            cartRepository.save(newItem); // Save the new item
        }
    }

    public List<CartResponse> getUserSpecificCartItem(int userId) {

        List<CartItems> cartItems = cartRepository.findByUserId(userId);

        List<CartResponse> cartResponseList = cartItems.stream()
                .map(item -> {
                    Glass product = glassRepository.findById(item.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + item.getProductId()));
                    return new CartResponse(product, item.getQuantity());
                })
                .collect(Collectors.toList());

       // System.out.println(cartResponseList);
        return cartResponseList;
    }

    public void deleteCartItemById(int userId, int productId) {

        CartItems cartItem = cartRepository.findByUserIdAndProductId(userId, productId).orElseThrow(() -> new ResourceNotFoundException("Product With ID: "+productId));
        cartRepository.delete(cartItem);
    }
}
