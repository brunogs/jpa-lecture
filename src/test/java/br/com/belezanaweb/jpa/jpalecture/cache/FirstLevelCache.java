package br.com.belezanaweb.jpa.jpalecture.cache;

import br.com.belezanaweb.jpa.jpalecture.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstLevelCache {

    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldUseCache() throws Exception {

        customerService.rewardCustomer();

    }
}
