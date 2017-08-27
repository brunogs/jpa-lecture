package br.com.belezanaweb.jpa.jpalecture.nativequeries;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectionsNative extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void ordersProjection() throws Exception {
        Query nativeQuery = entityManager.createNativeQuery("select o.total, c.name from orders o join customers c on o.customer_id = c.id");
        List<Object[]> orders = nativeQuery.getResultList();

        List<OrderDTO> orderDTOS = orders.stream()
                .map(objects -> new OrderDTO((BigDecimal) objects[0], (String) objects[1]))
                .collect(Collectors.toList());

        System.out.println(orderDTOS);
        Assert.assertTrue(!orderDTOS.isEmpty());
    }

    @Test
    public void customersProjection() throws Exception {
        Query nativeQuery = entityManager.createNativeQuery("select c.id as customerId, c.name as customerName, c.age as customerAge " +
                "from customers c", "CustomerMapping");

        List<Customer> customers = nativeQuery.getResultList();

//        List<OrderDTO> orderDTOS = orders.stream()
//                .map(objects -> new OrderDTO((BigDecimal) objects[0], (String) objects[1]))
//                .collect(Collectors.toList());

        System.out.println(customers);
        Assert.assertTrue(!customers.isEmpty());
    }
}
