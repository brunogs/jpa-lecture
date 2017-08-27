package br.com.belezanaweb.jpa.jpalecture.jpql;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class Projections extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void avgPriece() throws Exception {
        Double avgPriece = entityManager.createQuery("select avg(o.total) from Order o", Double.class).getSingleResult();
        assertEquals(116.78, avgPriece, 0.1);
    }

    @Test
    public void sumTotal() throws Exception {
        BigDecimal sumTotal = entityManager.createQuery("select sum(o.total) from Order o", BigDecimal.class).getSingleResult();
        assertEquals(new BigDecimal("116.78"), sumTotal);
    }
}
