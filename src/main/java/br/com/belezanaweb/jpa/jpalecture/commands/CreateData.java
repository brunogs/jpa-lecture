package br.com.belezanaweb.jpa.jpalecture.commands;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class CreateData {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CreateData(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Bean
    public CommandLineRunner create() {
        return (args) -> {
            log.info("Simple customers");

            Customer firstCustomer = customerRepository.save(new Customer("Bruno", 27));
            customerRepository.save(new Customer("Wellif", 24));
            customerRepository.save(new Customer("Jose", 24));

            orderRepository.save(new Order(firstCustomer));

            customerRepository.findAll().forEach(c -> log.info(String.format("customer=%s", c.getName())));

        };
    }

}
