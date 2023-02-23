package br.com.livefood.orders.controller;

import br.com.livefood.orders.domain.dto.OrderDTO;
import br.com.livefood.orders.domain.dto.OrderStatusDTO;
import br.com.livefood.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<OrderDTO> listarPorId(@PathVariable @NotNull Long id) {
        OrderDTO dto = service.findOrderById(id);

        return  ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> realizaPedido(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder) {
        OrderDTO pedidoRealizado = service.saveOrder(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> atualizaStatus(@PathVariable Long id, @RequestBody OrderStatusDTO status){
        OrderDTO dto = service.updateOrder(id, status);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        service.approveOrder(id);

        return ResponseEntity.ok().build();

    }

}
