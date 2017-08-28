package br.com.belezanaweb.jpa.jpalecture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderDTO {

    private BigDecimal totall;

    private String customer;

}
