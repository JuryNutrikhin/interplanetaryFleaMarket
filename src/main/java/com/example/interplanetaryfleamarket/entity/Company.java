package com.example.interplanetaryfleamarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "company_t")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="company_name_f")
    private  String companyName;

    @Column(name="company_address_f",nullable = false)
    private String companyAddress;

    @Column(name="telephone_f",nullable = false)
    private  String telephone;

    @Column(name= "company_email_f")
    private String companyEmail;
}
