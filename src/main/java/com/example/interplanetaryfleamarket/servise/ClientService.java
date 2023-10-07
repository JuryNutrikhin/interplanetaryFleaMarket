package com.example.interplanetaryfleamarket.servise;


import com.example.interplanetaryfleamarket.entity.Client;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ClientService  {
    Iterable<Client> findAll();
    Optional<Client> findById(Integer id);
    Optional<Client> add(Client client);
    Optional<Client> deleteById(Integer id);
    Optional<Client> updateById(Integer id, Client client);
}
