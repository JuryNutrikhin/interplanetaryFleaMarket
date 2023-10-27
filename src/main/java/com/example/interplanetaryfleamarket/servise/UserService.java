package com.example.interplanetaryfleamarket.servise;

import com.example.interplanetaryfleamarket.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


//сервис добавления пользователя
@Service
public interface UserService    {

    //метод регистрации пользователя
    //возвращает true если регистрация успешная
    boolean register(String login,
                     String password,
                     String passwordConfirmation,
                     String clientName,
                     String clientEmail,
                     String clientAddress);

    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String name);
}
