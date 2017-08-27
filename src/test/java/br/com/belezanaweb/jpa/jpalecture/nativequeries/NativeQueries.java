package br.com.belezanaweb.jpa.jpalecture.nativequeries;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NativeQueries extends JpaLectureApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void nativeQueryBasic() throws Exception {
        Query nativeQuery = entityManager.createNativeQuery("select *  from customers c", Customer.class);
        List<Customer> customers = nativeQuery.getResultList();

        System.out.println(customers);
        Assert.assertTrue(!customers.isEmpty());
    }

}
