package com.example.interplanetaryfleamarket.servise;

import com.example.interplanetaryfleamarket.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ProductService {

    Iterable<Product> findAll();
    Optional<Product> findById(Integer id);
    Optional<Product> add(Product product);
    Optional<Product> deleteById(Integer id);
    Optional<Product> updateById(Integer id,Product product);

    Iterable<Product> getProductGenre(String genre);
    Iterable<Product> getProductString(String string);
}
