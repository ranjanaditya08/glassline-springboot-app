package com.spring.Glassline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String type;
    private String imageLink;
    private String specName;
    private String description;
    private String price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", specName='" + specName + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }


}
