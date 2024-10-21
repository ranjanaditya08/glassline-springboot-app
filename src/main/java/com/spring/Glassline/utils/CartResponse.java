package com.spring.Glassline.utils;

import com.spring.Glassline.entity.Glass;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartResponse {

    private Glass product;
    private int quantity;
}
