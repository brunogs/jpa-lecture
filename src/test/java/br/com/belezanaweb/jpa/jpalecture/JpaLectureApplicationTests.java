package br.com.belezanaweb.jpa.jpalecture;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Random;

import static java.util.stream.IntStream.range;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaLectureApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        Random random = new Random();
        range(0, 50).forEach(customerIndex -> {
            int randomAge = random.nextInt(50) + 10;
            Customer customer = customerRepository.save(new Customer("customer" + customerIndex, randomAge));
            range(1, random.nextInt(50) + 1)
                    .forEach(orderIndex -> orderRepository.save(new Order(customer, BigDecimal.valueOf(orderIndex))));
        });
    }

}
