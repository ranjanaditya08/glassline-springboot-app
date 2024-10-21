package com.spring.Glassline.utils;

import lombok.Data;

@Data
public class CartRequest {

    private int userId;
    private int productId;
    private int quantity;
}
