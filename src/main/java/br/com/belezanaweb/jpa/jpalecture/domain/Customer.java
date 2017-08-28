package br.com.belezanaweb.jpa.jpalecture.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.EnumSet;
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
@Data
@NoArgsConstructor
@ToString(exclude = {"orders", "features"})
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    @ElementCollection
    @CollectionTable(name = "customers_features", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<CustomerFeature> features = EnumSet.noneOf(CustomerFeature.class);

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void addFeature(CustomerFeature feature) {
        this.features.add(feature);
    }

}
