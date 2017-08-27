package br.com.belezanaweb.jpa.jpalecture.lazy;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import br.com.belezanaweb.jpa.jpalecture.service.CustomerService;
import org.hibernate.LazyInitializationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazyLoad {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void setUp() throws Exception {
        Random random = new Random();
        range(0, 100).forEach(customerIndex -> {
            int randomAge = random.nextInt(50) + 10;
            Customer customer = customerRepository.save(new Customer("customer" + customerIndex, randomAge));
            range(1, random.nextInt(100) + 1)
                    .forEach(orderIndex -> orderRepository.save(new Order(customer, BigDecimal.valueOf(orderIndex))));
        });
    }

    @Test(expected = LazyInitializationException.class)
    public void totalByCustomerWithLazyLoadingWithoutTransaction() throws Exception {
        List<Customer> customers = customerRepository.findAll();
        Map<String, BigDecimal> totalByCustomer = customers.stream()
                .collect(toMap(Customer::getName, Customer::getTotalOrders));

        printTotalByCustomer(totalByCustomer);
    }

    @Test
    public void totalByCustomerWithLazyLoading() throws Exception {
        Map<String, BigDecimal> totalByCustomer = customerService.getTotalByCustomer();
        printTotalByCustomer(totalByCustomer);
    }

    @Test
    public void totalByCustomerWithEagerLoading() throws Exception {
        Map<String, BigDecimal> totalByCustomer = customerService.getTotalByCustomerEager();
        printTotalByCustomer(totalByCustomer);
    }

    @Test
    public void totalByOldestCustomers() throws Exception {
        Map<String, BigDecimal> totalByCustomer = customerService.getTotalByOldestsCustomers();
        printTotalByCustomer(totalByCustomer);
    }

    private void printTotalByCustomer(Map<String, BigDecimal> totalByCustomer) {
        totalByCustomer.forEach( (customer, total) ->
                System.out.println(customer + ", " + total)
        );
    }

}