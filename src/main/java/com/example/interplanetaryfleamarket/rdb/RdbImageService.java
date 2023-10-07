package com.example.interplanetaryfleamarket.rdb;

import com.example.interplanetaryfleamarket.entity.Image;
import com.example.interplanetaryfleamarket.rdb.repository.ImageRepository;
import com.example.interplanetaryfleamarket.servise.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RdbImageService implements ImageService {

    private  final ImageRepository imageRepository;


    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image add(Image image) {
        return imageRepository.save(image);
    }
}
