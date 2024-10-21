package com.spring.Glassline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "glasses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Glass {

    @Id
    private int id;
    private String categoryId;
    private String type;
    private String imageSrc;
    private String specsName;
    private String description;
    private String price;

}
