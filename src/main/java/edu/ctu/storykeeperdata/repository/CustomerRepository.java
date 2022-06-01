package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{ 'email' : { $regex : ?0, $options: 'i' } }")
    List<Customer> findAllByEmailContains(String email);

    @Query("{ 'last_name' : { $regex : ?0, $options: 'i' } }")
    List<Customer> findAllByLastNameContains(String lastName);

    @Query("{ 'phone' : { $regex : ?0, $options: 'i' } }")
    List<Customer> findAllByPhoneContains(String phone);
}
