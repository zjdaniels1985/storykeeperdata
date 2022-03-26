package edu.ctu.storykeeperdata.service;


import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;

    public long getCollectionCount() {return repository.count(); }

    public void save(final Order order) {
        repository.insert(order);
    }

    public void delete(String id) {
        Optional<Order> foundOrder = repository.findById(id);
        foundOrder.ifPresent(repository::delete);
    }
}
