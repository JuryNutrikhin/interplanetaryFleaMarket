package com.example.interplanetaryfleamarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "client_t")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "client_name_f",nullable = false)
    private  String clientName;


    @Column(name = "client_address_f")
    private  String clientAddress;

    @Column(name = "client_email_f",nullable = false)
    private  String clientEmail ;

    @Column(name = "registration_f",nullable = false)
    private LocalDate registration;



    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

}
