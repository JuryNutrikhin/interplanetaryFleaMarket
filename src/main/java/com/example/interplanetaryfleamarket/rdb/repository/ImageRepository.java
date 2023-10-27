package com.example.interplanetaryfleamarket.rdb.repository;

import com.example.interplanetaryfleamarket.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,Integer> {
}
