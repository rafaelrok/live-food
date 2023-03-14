package br.com.livefood.orders.domain.dto;

import br.com.livefood.orders.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rafae
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {

    private OrderStatus orderStatus;
}
