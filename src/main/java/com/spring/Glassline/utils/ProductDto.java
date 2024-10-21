package com.spring.Glassline.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String categoryId;
    private String type;
    private String imageSrc;
    private String specsName;
    private String description;
    private String price;
    private int quantity;
}
