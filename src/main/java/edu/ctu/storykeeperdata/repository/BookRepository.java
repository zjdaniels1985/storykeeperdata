package edu.ctu.storykeeperdata.repository;


import edu.ctu.storykeeperdata.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ 'title' : { $regex : ?0, $options: 'i' } }")
    List<Book> findAllByTitleContains(String title);

    @Query("{ 'author' : { $regex : ?0, $options: 'i' } }")
    List<Book> findAllByAuthorContains(String author);

    Optional<List<Book>> findAllByIsbnContains(String Isbn);


}
