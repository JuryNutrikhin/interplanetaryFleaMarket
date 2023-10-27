package com.example.interplanetaryfleamarket.controller;

import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.entity.Product;
import com.example.interplanetaryfleamarket.entity.User;
import com.example.interplanetaryfleamarket.servise.ClientService;
import com.example.interplanetaryfleamarket.servise.ProductService;
import com.example.interplanetaryfleamarket.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final ProductService productService;
    private final UserService userService;
    private final ClientService clientService;



    @GetMapping("")
    public String home(Model model) {


//        UserDetails userDetails = (UserDetails) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//        String login = userDetails.getUsername();
//        Optional<User> user = userService.findByLogin(login);
//        Client client = clientService.findClientByUser(user);

        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
//        model.addAttribute("client", client);
        return "index";

    }

    @GetMapping("login")
    public String login() {

        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "registration";
    }


    @PostMapping("register")
    public String register(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String passwordConformation,
            @RequestParam String clientEmail,
            @RequestParam String clientName,
            @RequestParam String clientAddress,
            RedirectAttributes redirectAttributes) {
        if (userService.register(login, password, passwordConformation, clientName,  clientEmail, clientAddress)) {
            redirectAttributes.addFlashAttribute("successMessage", "Вы успешно зарегистрированы.Выполните вход");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ошибка регистрации");
        }
        return "redirect:/login";
    }


}
