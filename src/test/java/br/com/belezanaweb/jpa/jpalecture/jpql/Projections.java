package br.com.belezanaweb.jpa.jpalecture.jpql;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Projections extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void avgPriece() throws Exception {
        Double avgPriece = entityManager.createQuery("select avg(o.total) from Order o", Double.class).getSingleResult();
        assertEquals(116.78, avgPriece, 0.);
    }

    @Test
    public void sumTotal() throws Exception {
        BigDecimal sumTotal = entityManager.createQuery("select sum(o.total) from Order o", BigDecimal.class).getSingleResult();
        assertEquals(new BigDecimal("116.78"), sumTotal);
    }

    @Test
    public void orderDTOResumeTuple() throws Exception {
        List<Tuple> tuples = entityManager.createQuery(
                "select o.total, o.customer.name from Order o", Tuple.class)
                .getResultList();

        List<OrderDTO> orderDTOS = tuples.stream()
                .map(tuple -> new OrderDTO(tuple.get("total", BigDecimal.class), tuple.get("name", String.class)))
                .collect(Collectors.toList());

        System.out.println(orderDTOS);

        assertTrue(!orderDTOS.isEmpty());
    }

    @Test
    public void orderDTOResumeObject() throws Exception {
        List<Object[]> orders = entityManager.createQuery(
                "select o.total, o.customer.name from Order o", Object[].class)
                .getResultList();

        List<OrderDTO> orderDTOS = orders.stream()
                .map(objects -> new OrderDTO((BigDecimal) objects[0], (String) objects[1]))
                .collect(Collectors.toList());

        System.out.println(orderDTOS);

        assertTrue(!orders.isEmpty());
    }

    @Test
    public void orderDTOResumeConstruct() throws Exception {
        List<OrderDTO> orders = entityManager.createQuery(
                "select new br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO(o.total, o.customer.name) from Order o", OrderDTO.class)
                .getResultList();

        System.out.println(orders);
        assertTrue(!orders.isEmpty());
    }
}
