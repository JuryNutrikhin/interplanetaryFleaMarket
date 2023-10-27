package com.example.interplanetaryfleamarket.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Objects;


//Сущность пользователь
@Data
@Entity
@Table(name = "user_t")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login_f",nullable = false)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;//пороль храниться в захештрованом виде

    @Column(name ="role_f",nullable = false)
    private String role;// ДЛЯ УПРОЩЕНИЯ РОЛИ ПИШУТСЯ В СТОЛБЦЕ В ВИДЕ СТРОКИ, ОДНА РОЛЬ

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @OneToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;



}
