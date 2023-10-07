package com.example.interplanetaryfleamarket.rdb;

import com.example.interplanetaryfleamarket.entity.Order;
import com.example.interplanetaryfleamarket.rdb.repository.OrderRepository;
import com.example.interplanetaryfleamarket.servise.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RdbOrderService implements OrderService {

    private  final OrderRepository orderRepository ;
    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> deleteById(Integer id) {
        Optional<Order> removable= findById(id);
        if(removable.isPresent()){
            orderRepository.deleteById(id);
        }
        return removable;  // вернем удаленный (empty если не с таким id)
    }

    @Override
    public Optional<Order> updateById(Integer id, Order order) {
        Optional<Order> updated = orderRepository.findById(id);
        if(updated.isPresent()){
            order.setId(id);
            return Optional.of(orderRepository.save(order)); // сохраняем новый данные
        }else {
            return Optional.empty();
        }
    }
}
