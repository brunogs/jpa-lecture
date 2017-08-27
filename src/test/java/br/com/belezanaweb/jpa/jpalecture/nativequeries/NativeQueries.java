package br.com.belezanaweb.jpa.jpalecture.nativequeries;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class NativeQueries extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void nativeQueryBasic() throws Exception {
        Query nativeQuery = entityManager.createNativeQuery("select *  from customers c", Customer.class);
        List<Customer> customers = nativeQuery.getResultList();
        System.out.println(customers);
    }

    @Test
    public void nativeQueryProjectionObject() throws Exception {
        Query nativeQuery = entityManager.createNativeQuery("select  * from orders c", Order.class);
        List<Order> customers = nativeQuery.getResultList();
        System.out.println(customers);
    }
}
