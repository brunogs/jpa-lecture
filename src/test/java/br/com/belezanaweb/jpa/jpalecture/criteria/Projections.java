package br.com.belezanaweb.jpa.jpalecture.criteria;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.math.BigDecimal;

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
}
