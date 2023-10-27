package com.example.interplanetaryfleamarket.rdb.repository;

import com.example.interplanetaryfleamarket.entity.Client;
import com.example.interplanetaryfleamarket.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    Iterable<Order> findAllById(Integer id);
    Iterable<Order> findAllByClient(Client client);
    Iterable<Order> findAllByClientId(Integer clientId);


}


