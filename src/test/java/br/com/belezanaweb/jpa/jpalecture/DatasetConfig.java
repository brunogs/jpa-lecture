package br.com.belezanaweb.jpa.jpalecture;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Random;

import static java.util.stream.IntStream.range;

@Configuration
public class DatasetConfig {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    void setup() {
        Random random = new Random();
        range(0, 50).forEach(customerIndex -> {
            int randomAge = random.nextInt(50) + 10;
            Customer customer = customerRepository.save(new Customer("customer" + customerIndex, randomAge));
            range(1, random.nextInt(50) + 1)
                    .forEach(orderIndex -> orderRepository.save(new Order(customer, BigDecimal.valueOf(orderIndex))));
        });
    }
}
