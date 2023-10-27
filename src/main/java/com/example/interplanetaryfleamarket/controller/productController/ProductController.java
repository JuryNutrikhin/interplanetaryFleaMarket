package com.example.interplanetaryfleamarket.controller.productController;


import com.example.interplanetaryfleamarket.entity.Image;
import com.example.interplanetaryfleamarket.entity.Product;
import com.example.interplanetaryfleamarket.servise.ImageService;
import com.example.interplanetaryfleamarket.servise.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    private  final ImageService imageService;



    @GetMapping("")
    public String productList(Model model){
        Iterable<Product> products = productService.findAll();
        if(products.iterator().hasNext()){
            model.addAttribute("products", products);
        }else{
            model.addAttribute("products", null);
        }
        return "product/product-list";

    }

// product-details
    @GetMapping("{id}")
    public String productDetails(@PathVariable Integer id,Model model){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            model.addAttribute("productId",product.get());
        }else {
            model.addAttribute("model",null);
        }
        return "product/detail";
    }

    @GetMapping("new-product")
    public String addProduct(Model model){
        Product product = new Product();

        model.addAttribute("product" , product  );
        return "product/product-form";
    }

    @PostMapping("new-product")
    public String addProduct(Product product, @RequestParam MultipartFile logoImage, RedirectAttributes redirectAttributes) throws IOException {

        // 1. добавить изображение
        String imageData = Base64.getEncoder().encodeToString(logoImage.getBytes());
        Image newImage =  new Image();
        newImage.setData(imageData);
        newImage = imageService.add(newImage);

        // 2. установить изображение компании
        product.setImage(newImage);
        Optional<Product>  newProduct = productService.add(product);

        if(newProduct.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage" , "Товар добавлен");
        }else {
            redirectAttributes.addFlashAttribute("errorMessage" , "Товар уже существует");
        }
        return "redirect:/product";//редирект на другую страницу


    }

    @GetMapping("update/{id}")
    public  String productEditForm(@PathVariable Integer id,Model model){
        Optional<Product> updated = productService.findById(id);
        if(updated.isPresent()){
            model.addAttribute("product",updated.get());
        }else {
            model.addAttribute("product", null);
        }
        return "product/product-edit";

    }

    @PostMapping("update/{id}")
    public  String productEditForm(@PathVariable Integer id,Product product,RedirectAttributes redirectAttributes){

        Optional<Product> updateProduct = productService.updateById(id,product);

        if(updateProduct.isPresent()){
            redirectAttributes.addFlashAttribute("successMessage",
                    "Товар " + updateProduct.get().getProductName() + " успешно добавлен");
        }else{
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Товар не изменён");
        }
        return "redirect:/product";
    }

    @GetMapping("delete/{id}")
    public String productDeleteId(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        Optional<Product> removed = productService.deleteById(id);
        if (removed.isPresent()) {
            redirectAttributes.addFlashAttribute("successMessage",  "Товар  успешно " +removed.get().getProductName() + " удален");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Товар не удален ,некорректный id");
        }
        return "redirect:/product";
    }


}
