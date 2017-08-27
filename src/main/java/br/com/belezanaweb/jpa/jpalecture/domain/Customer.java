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
                entityClass = Customer.class, //nao funciona com entidades nao gerenciadas
                fields = {
                        @FieldResult(name = "id", column = "customerId"),
                        @FieldResult(name = "name", column = "customerName"),
                        @FieldResult(name = "age", column = "customerAge")}))
@Entity
@Table(name = "customers")
@EqualsAndHashCode(of = "name")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Customer() {
    }

    public BigDecimal getTotalOrders() {
        return getOrders().stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
