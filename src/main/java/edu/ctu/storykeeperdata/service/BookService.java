package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;


    // get a count of book records from the collection
    public long getCollectionCount() {
        return repository.count();
    }

    // get all books from the collection
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // get all books by the title from the collection
    public List<Book> getBooksByTitle(String title) {
        return repository.findAllByTitleContains(title);
    }

    public List<Book> getBooksByISBN(String isbn) {
        Optional<List<Book>> foundBook = repository.findAllByIsbnContains(isbn);
        return foundBook.orElseGet(ArrayList::new);
    }

    // save new book record in the collection
    public void save(final Book book) {
        repository.insert(book);
    }

    public void delete(final String id) {
        Optional<Book> foundBook = repository.findById(id);
        System.out.println("Book to delete: " + foundBook);
        foundBook.ifPresent(repository::delete);
    }
}
