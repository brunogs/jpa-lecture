package br.com.belezanaweb.jpa.jpalecture.extras;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import static br.com.belezanaweb.jpa.jpalecture.domain.CustomerFeature.SHOWCASES;
import static br.com.belezanaweb.jpa.jpalecture.domain.OrderStatus.SENT;

public class ElementCollection extends JpaLectureApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void elementCollection() throws Exception {

        Customer helmet = new Customer("Helmet", 24);
        helmet.addFeature(SHOWCASES);

        customerRepository.save(helmet);

        List<Customer> customers = customerRepository.findByFeaturesIn(EnumSet.of(SHOWCASES));
        Assert.assertFalse(customers.isEmpty());
    }

    @Test
    public void elementMap() throws Exception {
        Optional<Order> order = orderRepository.findById(10L);
        order.ifPresent(o -> {
            o.setStatus(SENT);
            orderRepository.save(o);
        });

        Order orderUpdated = orderRepository.findById(10L).get();
        Assert.assertFalse(orderUpdated.getHistoricStatus().isEmpty());
    }
}
