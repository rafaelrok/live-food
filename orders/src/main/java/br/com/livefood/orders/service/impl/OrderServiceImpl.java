package br.com.livefood.orders.service.impl;

import br.com.livefood.orders.domain.dto.OrderDTO;
import br.com.livefood.orders.domain.dto.OrderStatusDTO;
import br.com.livefood.orders.domain.entity.Order;
import br.com.livefood.orders.enums.OrderStatus;
import br.com.livefood.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author rafae
 */

@Component
@RequiredArgsConstructor
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public Page<OrderDTO> findAllOrders(Pageable pageable) {
        Page<Order> order = orderRepository.findAll(pageable);
        return order.map(p -> modelMapper.map(p, OrderDTO.class));
    }

    public OrderDTO findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.getOrderItens().forEach(item -> item.setOrder(order));
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatusDTO statusDTO) {
        Order order = orderRepository.findByIdWithOrderItens(id);

        if (order == null){
            throw new RuntimeException("Order not Updated");
        }
        order.setOrderStatus(statusDTO.getOrderStatus());
        orderRepository.updateStatus(order.getOrderStatus(), order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public void approvePaymentOrder(Long id) {
        Order order = orderRepository.findByIdWithOrderItens(id);

        if (order == null){
            throw new RuntimeException("Order not approved");
        }
        order.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.updateStatus(order.getOrderStatus(), order);
    }

}
