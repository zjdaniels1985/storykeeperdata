package edu.ctu.storykeeperdata.service;


import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;


    public long getCollectionCount() {return repository.count(); }

    public List<Order> getAllOrders() { return repository.findAll(); }

    public Order getOrderByEmail(String email) {
        Order order = repository.findDistinctByCustomerEmailContains(email);
        return order;
    }

    public Order save(final Order order) {
        repository.insert(order);
        return order;
    }

    public Order delete(final String email) {
        Order orderToDelete = repository.findDistinctByCustomerEmailContains(email);
        repository.delete(orderToDelete);
        return orderToDelete;

    }
}
