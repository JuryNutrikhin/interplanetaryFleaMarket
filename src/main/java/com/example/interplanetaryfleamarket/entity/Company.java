package com.example.interplanetaryfleamarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name= "company_t")

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








    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(companyAddress, company.companyAddress) && Objects.equals(telephone, company.telephone) && Objects.equals(companyEmail, company.companyEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyAddress, telephone, companyEmail);
    }
}
