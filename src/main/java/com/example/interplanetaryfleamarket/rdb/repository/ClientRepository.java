package com.example.interplanetaryfleamarket.rdb.repository;

import com.example.interplanetaryfleamarket.entity.Client;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Integer> {
    Optional<Client> findByClientName(String clientName);

//    Iterable<Client> findAllByClient(Client client);
}

