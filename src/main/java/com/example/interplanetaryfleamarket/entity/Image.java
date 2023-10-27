package com.example.interplanetaryfleamarket.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data//  новое добавил
@Table(name = "image_t")
public class Image {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String data;





    public Image() {
    }

    public Image(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

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
