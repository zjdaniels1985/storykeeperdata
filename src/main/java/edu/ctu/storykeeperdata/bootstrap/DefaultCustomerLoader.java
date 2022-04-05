package edu.ctu.storykeeperdata.bootstrap;

import com.github.javafaker.Faker;
import edu.ctu.storykeeperdata.model.Address;
import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

// Lombok annotation for logger
@Slf4j
// Spring annotation
@Component
public class DefaultCustomerLoader implements CommandLineRunner, Ordered {

    private final CustomerService service;

    private final Faker faker;

    @Autowired
    DefaultCustomerLoader(CustomerService svc, Faker faker1) {
        this.service = svc;

        this.faker = faker1;
    }

    // will add the dummy employee data in the mongodb collection
    // will be executed automatically on the application startup
    @Override
    public void run(String... args) throws Exception {
        log.info("Running CustomerLoader");
        if (service.getCollectionCount() == 0) {
            log.info("Saving default customers in the collection");
            for (int i = 0; i < 11; i++) {
                persist();
            }
        } else {
            log.info("Default customers are already present in the mongo collection");
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }

    // calls the service layer method which in turn calls the dao layer method
    // to save the book record in the mongodb collection
    private void persist() {
        final Customer e = createCustomer();
        service.save(e);
    }

    // using the faker library to create some mock data for the book model
    private Customer createCustomer() {
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String email = faker.name().username() + "@email.com";
        final String phone = faker.number().digits(10);
        final Address address = new Address(faker.address().streetAddress(), faker.address().city(),
                faker.address().state(), faker.address().zipCode());

        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .address(address)
                .build();
    }
}
