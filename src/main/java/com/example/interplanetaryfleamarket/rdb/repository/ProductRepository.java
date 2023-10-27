package com.example.interplanetaryfleamarket.rdb.repository;

import com.example.interplanetaryfleamarket.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    Optional<Product> findByProductName(String name);

    Iterable<Product> findAllByGenre(String genre);


}
