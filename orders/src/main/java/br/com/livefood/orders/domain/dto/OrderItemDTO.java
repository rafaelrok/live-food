package br.com.livefood.orders.domain.dto;

import lombok.*;

/**
 * @author rafae
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;
    private Integer quantity;
    private String description;

}
