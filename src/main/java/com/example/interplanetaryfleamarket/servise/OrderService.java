package com.example.interplanetaryfleamarket.servise;

import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.entity.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface OrderService {
    Iterable<Order> findAll();
    Optional<Order> findById(Integer id);
    Iterable<Order> findAllByClientId(Integer clientId);
    Order add(Order order);
    Optional<Order> deleteById(Integer id);
    Optional<Order> updateById(Integer id,Order order);
    Iterable<Order> findAllByClient(Client client);
}
