package br.com.livefood.payments.controller;

import br.com.livefood.payments.domain.dto.PaymentDTO;
import br.com.livefood.payments.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * @author rafae
 */

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public Page<PaymentDTO> findAllPayment(@PageableDefault(size = 10) Pageable pageable) {
        return service.findAllPayment(pageable);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> savePayment(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriComponentsBuilder){
        PaymentDTO payment = service.savePayment(paymentDTO);
        URI uri = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(payment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO payment = service.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findPaymentById(@PathVariable @NotNull Long id) {
        PaymentDTO payment = service.findPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name= "updateOrder", fallbackMethod = "paymentApproveWithIntegrationPedding")
    public void confirmPayment(@PathVariable @NotNull Long id){
        service.confirmPayment(id);
    }

    public void paymentApproveWithIntegrationPedding(Long id, Exception e){
        service.updateStatus(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable @NotNull Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
