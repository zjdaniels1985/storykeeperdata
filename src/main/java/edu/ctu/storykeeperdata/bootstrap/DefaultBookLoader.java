package edu.ctu.storykeeperdata.bootstrap;

import com.github.javafaker.Faker;
import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

// Lombok annotation for logger
@Slf4j
// Spring annotation
@Component
public class DefaultBookLoader implements CommandLineRunner, Ordered {

    private final BookService service;
    private final Faker faker;

    @Autowired
    DefaultBookLoader(BookService bookService, Faker faker1) {
        this.service = bookService;
        this.faker = faker1;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running BookLoader");
        if (service.getCollectionCount() == 0) {
            log.info("Saving default books in the collection");
            for (int i = 0; i < 11; i++) {
                persist();
            }
        } else {
            log.info("Default books are already present in the mongo collection");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }

    // calls the service layer method which in turn calls the dao layer method
    // to save the book record in the mongodb collection
    private void persist() {
        final Book e = createBook();
        service.save(e);
    }

    // using the faker library to create some mock data for the book model
    private Book createBook() {
        final String title = faker.name().title();
        final String author = faker.name().fullName();
        final String publisher = faker.company().name();
        final String isbn = faker.number().digits(13);
        final String category = "Fiction";
        final double priceEach = Math.round(100 * faker.number().randomDouble(2, 1, 100) / 100);
        final int qty = faker.number().numberBetween(1, 10);

        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .category(category)
                .priceEach(priceEach)
                .qty(qty)
                .build();
    }
}
