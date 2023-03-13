package br.com.livefood.orders.controller;

import br.com.livefood.orders.domain.dto.OrderDTO;
import br.com.livefood.orders.domain.dto.OrderStatusDTO;
import br.com.livefood.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import javax.validation.constraints.NotNull;

/**
 * @author rafae
 */

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public Page<OrderDTO> findAll(Pageable pageable) {
        return service.findAllOrders(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable @NotNull Long id) {
        OrderDTO dto = service.findOrderById(id);

        return  ResponseEntity.ok(dto);
    }

    /**
     * @return the port of the server that received the request with load-balance
     */
    @GetMapping("/port")
    public String returnPort(@Value("${local.server.port}") String port) {
        return String.format("Request Port: %s", port);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder) {
        OrderDTO order = service.saveOrder(dto);

        URI url = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(url).body(order);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatusDTO status){
        OrderDTO dto = service.updateOrder(id, status);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}/paid")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        service.approveOrder(id);

        return ResponseEntity.ok().build();

    }

}
