package com.example.interplanetaryfleamarket.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "image_t")
public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    private Integer id;
//
//    @Lob
//    @Column(columnDefinition="MEDIUMBLOB")
//    private String data;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
//    @Column(columnDefinition = "bytea")
    @Column(columnDefinition = "MEDIUMBLOB")

    private String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
