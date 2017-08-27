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
import java.util.Random;

import static java.util.stream.IntStream.range;

@Slf4j
@SpringBootApplication
public class JpaLectureApplication implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaLectureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();
		range(0, 50).forEach(customerIndex -> {
			int randomAge = random.nextInt(50) + 10;
			Customer customer = customerRepository.save(new Customer("customer" + customerIndex, randomAge));
			range(1, random.nextInt(50) + 1)
					.forEach(orderIndex -> orderRepository.save(new Order(customer, BigDecimal.valueOf(orderIndex))));
		});
	}
}
