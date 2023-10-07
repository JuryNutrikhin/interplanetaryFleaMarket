package com.example.interplanetaryfleamarket.servise;

import com.example.interplanetaryfleamarket.entity.Company;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface CompanyService {
    Iterable<Company> findAll();
    Optional<Company> findById(Integer id);
    Optional<Company> add(Company company);
    Optional<Company> deleteById(Integer id);
    Optional<Company> updateById(Integer id,Company company);
}
