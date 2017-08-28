package br.com.belezanaweb.jpa.jpalecture.domain;


import br.com.belezanaweb.jpa.jpalecture.dto.OrderDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SqlResultSetMapping(
        name = "OrderCustomerMapping",
        classes = @ConstructorResult(
                targetClass = OrderDTO.class, //funciona com entidade nao gerenciadas (POJOs)
                columns = {
                        @ColumnResult(name = "total", type = BigDecimal.class),
                        @ColumnResult(name = "name", type = String.class)
                }
        )
)
@Entity
@Table(name = "orders")
@Data
@ToString(exclude = "historicStatus")
@EqualsAndHashCode(exclude = "historicStatus")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_status", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Map<LocalDate, OrderStatus> historicStatus = new HashMap<>();

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

    public void setStatus(OrderStatus status) {
        historicStatus.put(LocalDate.now(), status);
    }
}
