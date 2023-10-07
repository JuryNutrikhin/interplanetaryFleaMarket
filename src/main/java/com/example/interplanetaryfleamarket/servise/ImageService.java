package com.example.interplanetaryfleamarket.servise;

import com.example.interplanetaryfleamarket.entity.Image;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ImageService {
    Iterable<Image> findAll();

    Optional<Image> findById(Integer id);

    Image add(Image image);
}
