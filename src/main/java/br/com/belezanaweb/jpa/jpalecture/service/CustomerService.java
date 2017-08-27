package br.com.belezanaweb.jpa.jpalecture.service;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Map<String, BigDecimal> getTotalByCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().collect(toMap(Customer::getName, Customer::getTotalOrders));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, BigDecimal> getTotalByCustomerEager() {
        List<Customer> customers = customerRepository.findAllWithOrders();
        return customers.stream().collect(toMap(Customer::getName, Customer::getTotalOrders));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, BigDecimal> getTotalByOldestCustomers() {
        List<Customer> oldestCustomers = customerRepository.findTop10ByAgeGreaterThanEqual(40);

        if (oldestCustomers.size() > 5) {
            return oldestCustomers.stream()
                    .collect(toMap(Customer::getName, Customer::getTotalOrders));
        } else {
            return Collections.emptyMap();
        }
    }

    @Transactional
    public void rewardCustomer() {
        List<Customer> customers = customerRepository.findTop10ByAgeGreaterThanEqual(30);

        Arrays.asList(customers.get(0), customers.get(1)).forEach(customer -> {

            customerRepository.findById(customer.getId())
                    .ifPresent(c -> System.out.println(c.getName()));

            customerRepository.findById(customer.getId())
                    .ifPresent(c -> System.out.println(c.getAge()));
            //Faz outra regra
        });
    }

}
