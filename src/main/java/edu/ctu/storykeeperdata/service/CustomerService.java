package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public long getCollectionCount() { return repository.count();}

    public List<Customer> getAllCustomers() { return repository.findAll(); }

    public void save(final Customer customer) { repository.insert(customer); }

    public void delete(String id) {
        Optional<Customer> foundCustomer = repository.findById(id);
        foundCustomer.ifPresent(repository::delete);
    }

    public List<Customer> getCustomerByEmail(String keyword) {
        Optional<List<Customer>> foundCustomer = repository.findAllByEmailContains(keyword);
        return foundCustomer.orElseGet(ArrayList::new);
    }

    public List<Customer> getCustomerByPhone(String keyword) {
        Optional<List<Customer>> foundCustomer = repository.findAllByPhoneContains(keyword);
        return foundCustomer.orElseGet(ArrayList::new);
    }
}
