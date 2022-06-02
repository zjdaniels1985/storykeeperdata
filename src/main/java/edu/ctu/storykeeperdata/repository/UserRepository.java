package edu.ctu.storykeeperdata.repository;

import edu.ctu.storykeeperdata.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
