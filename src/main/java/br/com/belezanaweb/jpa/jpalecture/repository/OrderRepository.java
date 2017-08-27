package br.com.belezanaweb.jpa.jpalecture.repository;

import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
