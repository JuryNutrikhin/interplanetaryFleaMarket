package com.example.interplanetaryfleamarket.controller.imageController;


import com.example.interplanetaryfleamarket.entity.Image;
import com.example.interplanetaryfleamarket.servise.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Controller
@RequestMapping("image")
public class ImageController {

    private  final ImageService imageService;

//    @GetMapping("")
//    public String findAll(Model model){
//        Iterable<Image> images = imageService.findAll();
//        model.addAttribute("images", images);
//        return "image/image-list";
//    }

//    @GetMapping("new")
//    public String addNew(){
//        return "image/image-form";
//    }

//    @PostMapping("new")
//    public String addNew(@RequestParam MultipartFile multipartFile ) throws IOException{
//
//        String imageData = Base64.getEncoder().encodeToString(multipartFile.getBytes());
//        Image newImage = new Image();
//        newImage.setData(imageData);
//        imageService.add(newImage);
//        return "redirect:/image";
//    }

    @GetMapping("")
    public String findAll(Model model) {
        Iterable<Image> images = imageService.findAll();
        model.addAttribute("images", images);
        return "image/image-list";
    }

    @GetMapping("new")
    public String addNew() {
        return "image/image-form";
    }

    @PostMapping("new")
    public String addNew(@RequestParam MultipartFile image) throws IOException {
        String imageData = Base64.getEncoder().encodeToString(image.getBytes());
        Image newImage = new Image();
        newImage.setData(imageData);
        imageService.add(newImage);
        return "redirect:/image";
    }

}
