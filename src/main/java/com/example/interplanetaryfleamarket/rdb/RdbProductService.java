package com.example.interplanetaryfleamarket.rdb;

import com.example.interplanetaryfleamarket.entity.Product;
import com.example.interplanetaryfleamarket.rdb.repository.ProductRepository;
import com.example.interplanetaryfleamarket.servise.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class RdbProductService implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> add(Product product) {
        if(productRepository.findByProductName(product.getProductName()).isPresent()){
        return Optional.empty(); }  // компания такая уже есть

        return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<Product> deleteById(Integer id) {
        Optional<Product> removable = findById(id);
        if(removable.isPresent()){
            productRepository.deleteById(id);
        }
        return removable;
    }

    @Override
    public Optional<Product> updateById(Integer id,Product product) {
        Optional<Product> updated = findById(id);
        Optional<Product> duplicatedByNameProduct = productRepository.findByProductName(product.getProductName());

        if(updated.isPresent() &&
                (duplicatedByNameProduct.isEmpty())|| Objects.equals(duplicatedByNameProduct.get().getId(),id)){
            product.setId(id);
            return Optional.of(productRepository.save(product)); // сохраняем новый данные
        }else{
        return Optional.empty();
        }
    }

//    @Override
//    Optional<Product> findByName(String name){
//
//    }
}
