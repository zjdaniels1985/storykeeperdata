package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.Customer;
import edu.ctu.storykeeperdata.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repository;

    public long getCollectionCount() { return repository.count();}

    public List<Customer> getAllCustomers() { return repository.findAll(); }

    public List<Customer> getCustomersByLastName(String lastName) {return repository.findAllByLastNameContains(lastName);}

    public Customer getCustomerByEmail(String email) { return repository.findDistinctByEmailEquals(email);}

    public Customer getCustomerByPhone(String phone) { return repository.findDistinctByPhoneContains(phone);}

    public Customer save(final Customer customer) { repository.insert(customer); return customer; }

    public Customer delete(final String email) {
        Customer foundCustomer = repository.findDistinctByEmailEquals(email);
        repository.delete(foundCustomer); return foundCustomer; }


}
