package br.com.belezanaweb.jpa.jpalecture.domain;

import br.com.belezanaweb.jpa.jpalecture.dto.CustomerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SqlResultSetMapping(
        name = "CustomerMapping",
        entities = @EntityResult(
                entityClass = Customer.class,
                fields = {
                        @FieldResult(name = "id", column = "customerId"),
                        @FieldResult(name = "name", column = "customerName"),
                        @FieldResult(name = "age", column = "customerAge")}))
@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public BigDecimal getTotalOrders() {
        return getOrders().stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
