package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

}
