package edu.ctu.storykeeperdata.controller;


import edu.ctu.storykeeperdata.model.Order;
import edu.ctu.storykeeperdata.service.OrderService;
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
public class OrderController {

    private final OrderService service;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> fetchAllOrders() {
        log.info("Fetching all orders from the db");
        try {
            List<Order> orders = service.getAllOrders();
            if (orders == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/email")
    public ResponseEntity<Order> fetchOrderByEmail(@RequestParam("email") String email) {
        log.info("Fetching all orders from the db where email = {}", email);
        try {
            Order order = service.getOrderByEmail(email);
            if (order == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        log.info("Adding " + order.toString() + " to the orders collection");
        try {
            service.save(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(order, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-order")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        log.info("Updating order: " + order.toString() + " in the orders collection.");
        try {
            service.save(order);
            return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(order, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-order")
    public ResponseEntity<Order> deleteCustomer(@RequestParam("email") String email) {
        log.info("Fetching and removing order with email: " + email + " from the orders collection.");
        try {
            service.delete(email);
            return new ResponseEntity<>(service.getOrderByEmail(email), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(service.getOrderByEmail(email), HttpStatus.NOT_FOUND);
        }
    }
}
