package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<List<Order>> findAllByCustomerEmailContains(String email);

    Optional<Order> findOrderById(String id);
}
