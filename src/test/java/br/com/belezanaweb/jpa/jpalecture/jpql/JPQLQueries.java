package br.com.belezanaweb.jpa.jpalecture.jpql;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JPQLQueries extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void Nqueries() throws Exception {
        List<Order> orders = orderRepository.findAll();
        System.out.println(orders);

    }

    @Test
    public void fetchQueries() throws Exception {
        List orders = entityManager.createNamedQuery("Order.findAllWithCustomer").getResultList();
        System.out.println(orders);

    }
}
