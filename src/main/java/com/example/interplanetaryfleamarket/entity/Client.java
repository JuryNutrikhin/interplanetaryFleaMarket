package com.example.interplanetaryfleamarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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



}
