package br.com.belezanaweb.jpa.jpalecture.criteria;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class Projections extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void avgPriece() throws Exception {
        Double avgPriece = entityManager.createQuery("select avg(o.total) from Order o", Double.class).getSingleResult();
        assertEquals(avgPriece, 116.78, 0.1);
    }
}
