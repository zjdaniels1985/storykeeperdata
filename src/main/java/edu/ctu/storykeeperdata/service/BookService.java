package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // get all books by the author from the collection
    public List<Book> getBooksByAuthor(String author) {
        return repository.findAllByAuthorContains(author);
    }

    // get all books by the publisher from the collection
    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbnContains(isbn);
    }

    // save new book record in the collection
    public void save(final Book book) {
        repository.insert(book);
    }

    public void delete(final String isbn) {
        Book foundBook = repository.findByIsbnContains(isbn);
        System.out.println("Book to delete: " + foundBook);
        repository.delete(foundBook);
    }
}
