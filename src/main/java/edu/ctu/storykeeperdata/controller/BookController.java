package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Lombok annotation for logger
@Slf4j
//Spring annotations
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService service;


    @GetMapping("/books")
    public ResponseEntity<List<Book>> fetchAllBooks() {
        log.info("Fetching all books from the db");
        try {
            List<Book> books = service.getAllBooks();
            if (books == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/books/title")
    public ResponseEntity<List<Book>> fetchBooksByTitle(@RequestParam("title") String title) {
        log.info("Fetching all books from the db where title = {}", title);
        try {
            List<Book> books = service.getBooksByTitle(title);
            if (books.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/books/author")
    public ResponseEntity<List<Book>> fetchBooksByAuthor(@RequestParam("author") String author) {
        log.info("Fetching all books from the db where author = {}", author);
        try {
            List<Book> books = service.getBooksByAuthor(author);
            if (books.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(books, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/isbn")
    public ResponseEntity<Book> fetchBooksByIsbn(@RequestParam("isbn") String isbn) {
        log.info("Fetching book from the db where ISBN = {}", isbn);
        try {
            Book book = service.getBookByIsbn(isbn);
            if (book == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        log.info("Adding: " + book.getTitle() + " to books collection.");
        try {
            service.save(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(book, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-book")
    public ResponseEntity<Book> updateBook(@RequestBody Book updateBook) {
        log.info("Updating book: " + updateBook.getTitle() + " in the books collection.");
        try {
            service.save(updateBook);
            return new ResponseEntity<>(updateBook, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(updateBook, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<Book> deleteBook(@RequestParam("isbn") String isbn) {
        log.info("Fetching and removing book with isbn: " + isbn + " from the books collection.");
        try {
            service.delete(isbn);
            return new ResponseEntity<>(service.getBookByIsbn(isbn), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(service.getBookByIsbn(isbn), HttpStatus.NOT_FOUND);
        }
    }

}
