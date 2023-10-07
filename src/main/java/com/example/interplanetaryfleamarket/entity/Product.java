package com.example.interplanetaryfleamarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "product_t")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name_f",nullable = false)
    private String productName;

    @Column(name = "description_f",nullable = false)
    private String description;


    @Column(name="product_foto_f")
    private  String productFoto;

    @Column(name = "product_price_f",nullable = false)
    private Integer productPrice;

    @Column(name= "gеnre_f",nullable = false)
    private  String genre;

    @Column(name = "product_count_f")
    private  Integer productCount;// количество товара на складе

    @Column(name = "product_date_create_f")
    private  String productDateCreate;

    @Column(name ="product_country_f",nullable = false)
    private String productCountry;

    @OneToMany(mappedBy = "product")
    private Set<Order> orders;

    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name="product_foto_id",referencedColumnName = "id")
    private Image image;
}
