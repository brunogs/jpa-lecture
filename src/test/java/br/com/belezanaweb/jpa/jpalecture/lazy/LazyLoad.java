package br.com.belezanaweb.jpa.jpalecture.lazy;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import br.com.belezanaweb.jpa.jpalecture.service.CustomerService;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazyLoad {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

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
