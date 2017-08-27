package br.com.belezanaweb.jpa.jpalecture.criteria;

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
    public void avgTotal() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);

        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        criteriaQuery.select(builder.avg(orderRoot.get("total")));

        TypedQuery<Double> query = entityManager.createQuery(criteriaQuery);
        Double avgTotal = query.getSingleResult();

        assertEquals(116.78, avgTotal, 0.1);


    }

    @Test
    public void sumTotal() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);

        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        criteriaQuery.select(builder.sum(orderRoot.get("total")));

        TypedQuery<BigDecimal> query = entityManager.createQuery(criteriaQuery);
        BigDecimal sumTotal = query.getSingleResult();

        assertEquals(new BigDecimal("116.78"), sumTotal);

    }

    @Test
    public void orderDTOObject() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        criteriaQuery.multiselect(orderRoot.get("total"), orderRoot.get("customer").get("name"));

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);

        List<Object[]> orders = query.getResultList();

        List<OrderDTO> orderDTOS = orders.stream()
                .map(objects -> new OrderDTO((BigDecimal) objects[0], (String) objects[1]))
                .collect(Collectors.toList());

        System.out.println(orderDTOS);

        assertTrue(!orders.isEmpty());

    }

    @Test
    public void orderDTOConstruct() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDTO> criteriaQuery = builder.createQuery(OrderDTO.class);

        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        criteriaQuery.select(builder.construct(OrderDTO.class, orderRoot.get("total"), orderRoot.get("customer").get("name")));

        TypedQuery<OrderDTO> query = entityManager.createQuery(criteriaQuery);
        List<OrderDTO> orders = query.getResultList();

        System.out.println(orders);
        assertTrue(!orders.isEmpty());

    }
}
