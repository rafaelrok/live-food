package br.com.livefood.payments.domain.dto;

import br.com.livefood.payments.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author rafae
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal amount;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long orderId;
    private Long paymentMathodId;
}
