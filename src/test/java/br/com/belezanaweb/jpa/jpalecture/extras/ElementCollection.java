package br.com.belezanaweb.jpa.jpalecture.extras;

import br.com.belezanaweb.jpa.jpalecture.JpaLectureApplicationTests;
import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EnumSet;
import java.util.List;

import static br.com.belezanaweb.jpa.jpalecture.domain.CustomerFeature.SHOWCASES;

public class ElementCollection extends JpaLectureApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void elementCollection() throws Exception {

        Customer helmet = new Customer("Helmet", 24);
        helmet.addFeature(SHOWCASES);

        customerRepository.save(helmet);

        List<Customer> customers = customerRepository.findByFeaturesIn(EnumSet.of(SHOWCASES));
        Assert.assertFalse(customers.isEmpty());
    }
}
