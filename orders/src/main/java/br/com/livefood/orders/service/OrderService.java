package br.com.livefood.orders.service;

import br.com.livefood.orders.domain.dto.OrderDTO;
import br.com.livefood.orders.domain.dto.OrderStatusDTO;
import br.com.livefood.orders.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author rafae
 */
@Service
public class OrderService {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    public Page<OrderDTO> findAllOrders(Pageable pageable) {
        return orderServiceImpl.findAllOrders(pageable);
    }

    public OrderDTO findOrderById(Long id) {
        return orderServiceImpl.findOrderById(id);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return orderServiceImpl.saveOrder(orderDTO);
    }

    public OrderDTO updateOrder(Long id, OrderStatusDTO statusDTO) {
        return orderServiceImpl.updateOrderStatus(id, statusDTO);
    }

    public void approveOrder(Long id) {
        orderServiceImpl.approvePaymentOrder(id);
    }
}
