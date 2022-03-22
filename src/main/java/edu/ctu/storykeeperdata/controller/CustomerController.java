package edu.ctu.storykeeperdata.controller;

import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Lombok annotation for logger
@Slf4j
// Spring annotations
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> fetchAllCustomers() {
        log.info("Fetching all customers from the db");
        try {
            List<Customer> customers = service.getAllCustomers();
            if (customers == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/lastname")
    public ResponseEntity<List<Customer>> fetchCustomersByLastName(@RequestParam("lastname") String lastName) {
        log.info("Fetching all customers from the db where last name = {}", lastName);
        try {
            List<Customer> customers = service.getCustomersByLastName(lastName);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/email")
    public ResponseEntity<Customer> fetchCustomerByEmail(@RequestParam("email") String email) {
        log.info("Fetching all customers from the db where email = {}", email);
        try {
            Customer customer = service.getCustomerByEmail(email);
            if (customer == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/phone")
    public ResponseEntity<Customer> fetchCustomersByPhone(@RequestParam("phone") String phone) {
        log.info("Fetching all customers from the db where phone = {}", phone);
        try {
            Customer customer = service.getCustomerByPhone(phone);
            if (customer == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        log.info("Adding " + customer.getFirstName() + " " + customer.getLastName() + " to customers collection.");
        try {
            service.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        log.info("Updating customer: " + customer.getFirstName() + " " + customer.getLastName()
                + " in the customers collection.");
        try {
            service.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(customer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-customer")
    public ResponseEntity<Customer> deleteCustomer(@RequestParam("email") String email) {
        log.info("Fetching and removing customer with email: " + email + " from the customers collection.");
        try {
            service.delete(email);
            return new ResponseEntity<>(service.getCustomerByEmail(email), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
