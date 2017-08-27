package br.com.belezanaweb.jpa.jpalecture.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal total;

    //TODO pensar bom exemplo com many-to-many
    //private Set<Product> products;


    public Order(Customer customer, BigDecimal total) {
        this.customer = customer;
        this.total = total;
    }

    public Order(Long id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }
}
