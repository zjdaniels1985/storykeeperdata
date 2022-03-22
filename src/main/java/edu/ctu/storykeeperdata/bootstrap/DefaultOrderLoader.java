package edu.ctu.storykeeperdata.bootstrap;

import edu.ctu.storykeeperdata.model.Book;
import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.service.BookService;
import edu.ctu.storykeeperdata.service.CustomerService;
import edu.ctu.storykeeperdata.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

// Lombok annotation for logger
@Slf4j
// Spring annotation
@Component
public class DefaultOrderLoader implements CommandLineRunner {

    private final OrderService orderService;
    private final CustomerService custService;
    private final BookService bookService;



    public DefaultOrderLoader(OrderService orderService, CustomerService custService, BookService bookService) {
        this.orderService = orderService;
        this.custService = custService;
        this.bookService = bookService;
    }

    // will add the dummy employee data in the mongodb collection
    // will be executed automatically on the application startup
    @Override
    public void run(String...args) {
        if (orderService.getCollectionCount() == 0) {
            log.info("Saving default orders in the collection");
            List<Customer> customers = custService.getAllCustomers();
            for (int i=0; i<11; i++) {
                Customer customer = customers.get(i);
                persist(customer);
            }
        } else {
            log.info("Default orders are already present in the orders collection");
        }
    }

    // calls the service layer method which in turn calls the dao layer method
    // to save the book record in the mongodb collection
    private void persist(Customer customer) {
        final Order e = createOrder(customer);
        orderService.save(e);
    }

    // using the faker library to create some mock data for the book model
    private Order createOrder(Customer customer) {
        final String customerEmail = customer.getEmail();
        final List<Book> items = bookService.getAllBooks();
        final double subTotal = 2.00;
        final double taxAmount = 0.99;
        final double grandTotal = subTotal + taxAmount;


        return Order.builder()
                .customerEmail(customerEmail)
                .items(items)
                .subTotal(subTotal)
                .taxAmount(taxAmount)
                .grandTotal(grandTotal)
                .build();
    }
}
