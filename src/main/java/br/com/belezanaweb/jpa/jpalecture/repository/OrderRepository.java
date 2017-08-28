package br.com.belezanaweb.jpa.jpalecture.repository;

import br.com.belezanaweb.jpa.jpalecture.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
