package com.example.interplanetaryfleamarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Data
@Table(name="order_t")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "count_f", nullable = false)
    private Integer count;

    @Column(name = "order_data_f")
    private LocalDate orderData;

    @Column(name = "payment_f", nullable = false)
    private Boolean payment;
    ///..........................................................
    //Новый код
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    ///..........................................................
    //новый код 2
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;


    //----------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
