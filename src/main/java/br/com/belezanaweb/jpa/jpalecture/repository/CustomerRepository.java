package br.com.belezanaweb.jpa.jpalecture.repository;

import br.com.belezanaweb.jpa.jpalecture.domain.Customer;
import br.com.belezanaweb.jpa.jpalecture.domain.CustomerFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select distinct c from Customer c left join fetch c.orders")
    List<Customer> findAllWithOrders();

    List<Customer> findTop10ByAgeGreaterThanEqual(int age);

    List<Customer> findByFeaturesIn(Set<CustomerFeature> features);
}
