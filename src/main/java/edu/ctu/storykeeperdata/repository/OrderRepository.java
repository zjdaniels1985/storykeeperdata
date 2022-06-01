package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'customer_email' : { $regex : ?0, $options: 'i' } }")
    List<Order> findAllByCustomerEmailContains(String email);

    Optional<Order> findById(String id);
}
