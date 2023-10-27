package com.example.interplanetaryfleamarket.rdb.security;

import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.entity.User;
import com.example.interplanetaryfleamarket.rdb.repository.ClientRepository;
import com.example.interplanetaryfleamarket.rdb.repository.UserRepository;
import com.example.interplanetaryfleamarket.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RdbUserService implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private  final ClientRepository clientRepository;

    @Override
    public boolean register(String login, String password, String passwordConfirmation,String clientName, String clientEmail,String clientAddress) {

        if(!password.equals(passwordConfirmation) || userRepository.findUserByLogin(login).isPresent()){
            return false;                 //Если пороли не совпали или логин такй сущществует то указать errorMassage
        }
        if(clientRepository.findByClientName(clientName).isPresent()) {
            return false;               //Если client  с таким именем есть то указать в errorMessage
        }


        Client client = new Client();
        client.setClientName(clientName);
        client.setClientEmail(clientEmail);
        client.setClientAddress(clientAddress);
        client.setRegistration(LocalDate.now());
//        client.setUser(user);
//        clientRepository.save(client);

        User user = new User();
        user.setLogin(login);
        user.setRole("USER"); //user -  по умолчанию
        user.setPassword(passwordEncoder.encode(password));
        user.setClient(client);
        userRepository.save(user);

//        client.setUser(user);
        clientRepository.save(client);
        return true;
    }

    @Override
    public Optional<User> findById(Integer id) {

        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {

        return userRepository.findUserByLogin(login);
    }
}
