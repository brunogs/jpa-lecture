package br.com.belezanaweb.jpa.jpalecture.criteria;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Subqueries extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void subqueriesOrdersWithCustomerAgeGreaterThan20() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDTO> criteriaQuery = builder.createQuery(OrderDTO.class);
        Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        Root<Order> customerRoot = subquery.from(Order.class);

        subquery.select(builder.avg(customerRoot.get("total")));

        criteriaQuery.where(builder.greaterThanOrEqualTo(orderRoot.get("total"), subquery));
        criteriaQuery.select(builder.construct(OrderDTO.class, orderRoot.get("total"), orderRoot.get("customer").get("name")));

        TypedQuery<OrderDTO> query = entityManager.createQuery(criteriaQuery);
        List<OrderDTO> orders = query.getResultList();

        System.out.println(orders);
        assertTrue(!orders.isEmpty());
    }
}
