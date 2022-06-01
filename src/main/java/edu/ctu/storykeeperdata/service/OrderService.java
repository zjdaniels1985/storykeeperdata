package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;

    public long getCollectionCount() { return repository.count(); }

    public List<Order> getAllOrders() { return repository.findAll(); }

    public Order getOrderById(String keyword) {
        Optional<Order> foundOrder = repository.findById(keyword);
        if (foundOrder.isPresent()) {
            return foundOrder.get();
        } else throw new NoSuchElementException();
    }

    public List<Order> getOrderByEmail (String keyword){
        return repository.findAllByCustomerEmailContains(keyword);
    }

    public void save (Order order){
        repository.insert(order);
    }

    public void delete (String keyword){
        Optional<Order> foundOrder = repository.findById(keyword);
        foundOrder.ifPresent(repository::delete);
    }

}
