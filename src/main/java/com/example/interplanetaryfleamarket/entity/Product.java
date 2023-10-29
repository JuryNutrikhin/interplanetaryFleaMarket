package com.example.interplanetaryfleamarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_t")
public class
Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name_f", nullable = false)
    private String productName;

//    @Column(name = "description_f", nullable = false)
//    @Column(name = "description_f", nullable = false)
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String description;


    @Column(name = "product_price_f", nullable = false)
    private Integer productPrice;

    @Column(name = "gеnre_f", nullable = false)
    private String genre;

    @Column(name = "product_count_f")
    private Integer productCount;// количество товара на складе

    @Column(name = "product_date_create_f")
    private String productDateCreate;

    @Column(name = "product_country_f", nullable = false)
    private String productCountry;



    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name="product_foto_id",referencedColumnName = "id")
    private Image image;
}




