package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public Book getBookById(String id) {
        return repository.findBookById(id);
    }

    // get all books by the title from the collection
    public List<Book> getBooksByTitle(String title){
        return repository.findAllByTitleContains(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return repository.findAllByAuthorContains(author);
    }

    public List<Book> getBooksByIsbn(String Isbn) {
        return repository.findAllByIsbnContains(Isbn);

    }

    // save new book record in the collection
    public void save(final Book book) {
        repository.save(book);
    }

    public void delete(final String id) {
        Optional<Book> foundBook = repository.findById(id);
        log.info("Book to delete: " + foundBook);
        foundBook.ifPresent(repository::delete);
    }
}
