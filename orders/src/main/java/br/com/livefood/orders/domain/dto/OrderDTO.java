package br.com.livefood.orders.domain.dto;

import br.com.livefood.orders.domain.entity.OrderItem;
import br.com.livefood.orders.domain.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private LocalDateTime dateTime;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();
}
