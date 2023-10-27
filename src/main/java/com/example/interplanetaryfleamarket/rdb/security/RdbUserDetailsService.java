package com.example.interplanetaryfleamarket.rdb.security;


import com.example.interplanetaryfleamarket.entity.User;
import com.example.interplanetaryfleamarket.rdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RdbUserDetailsService implements UserDetailsService {


    //используем источник данных
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(username);//дергаем логин из базы данных

        if(user.isEmpty()){
            return null;
        }

        //если user существует сформировать UserDetails и вернуть

        RdbUserDetails userDetails = new RdbUserDetails();
        userDetails.setUser(user.get());


        return userDetails;
    }
}

