package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'customer_email' : { $regex : ?0, $options: 'i' } }")
    Order findDistinctByCustomerEmailContains(String email);
}
