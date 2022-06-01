package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public long getCollectionCount() { return repository.count();}

    public List<Customer> getAllCustomers() { return repository.findAll(); }

    public Customer getCustomerById(String keyword) {
        Optional<Customer> foundCustomer = repository.findById(keyword);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        } else throw new NoSuchElementException();
    }

    public List<Customer> getCustomersByLastName(String keyword) {
        return repository.findAllByLastNameContains(keyword);
    }

    public List<Customer> getCustomersByEmail(String keyword) {
        return repository.findAllByEmailContains(keyword);

    }

    public List<Customer> getCustomersByPhone(String keyword) {
        return repository.findAllByPhoneContains(keyword);
    }

    public void save(final Customer customer) { repository.save(customer); }

    public void delete(String id) {
        Optional<Customer> foundCustomer = repository.findById(id);
        foundCustomer.ifPresent(repository::delete);
    }
}
