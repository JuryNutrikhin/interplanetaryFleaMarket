package com.example.interplanetaryfleamarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="order_t")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "count_f",nullable = false)
    private Integer count;

    @Column(name ="order_data_f")
    private Date orderData;

    @Column(name = "payment_f",nullable = false)
    private Boolean payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;  //?

    @ManyToOne
    @JoinColumn(name= "product_id")
    private  Product product;


}
