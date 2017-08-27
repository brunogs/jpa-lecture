package br.com.belezanaweb.jpa.jpalecture;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import br.com.belezanaweb.jpa.jpalecture.repository.CustomerRepository;
import br.com.belezanaweb.jpa.jpalecture.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class JpaLectureApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaLectureApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("Simple customers");

		Customer firstCustomer = customerRepository.save(new Customer("Bruno", 24));
		customerRepository.save(new Customer("Wellif", 19));
		customerRepository.save(new Customer("Jose", 19));

		orderRepository.save(new Order(firstCustomer, new BigDecimal(116.78)));

		customerRepository.findAll().forEach(c -> log.info(String.format("customer=%s", c.getName())));

	}
}
