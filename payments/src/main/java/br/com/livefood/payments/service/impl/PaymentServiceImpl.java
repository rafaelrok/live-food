package br.com.livefood.payments.service.impl;

import br.com.livefood.payments.client.OrderClient;
import br.com.livefood.payments.domain.dto.PaymentDTO;
import br.com.livefood.payments.domain.entity.Payment;
import br.com.livefood.payments.enums.Status;
import br.com.livefood.payments.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


/**
 * @author rafae
 */

@Component
public class PaymentServiceImpl {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderClient orderClient;

    public Page<PaymentDTO> findAllPayment(Pageable pageable) {
        Page<Payment> page = repository.findAll(pageable);
        return page.map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    public PaymentDTO findPaymentById(Long id) {
        Payment payment = repository.findById(id).orElseThrow();
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setStatus(Status.PENDING);
        repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setId(id);
        repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void confirmPayment(Long id) {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isEmpty()) {
            throw new RuntimeException("Payment not found");
        }

        payment.get().setStatus(Status.APPROVED);
        repository.save(payment.get());
        orderClient.approvePayment(payment.get().getOrderId());
    }

    public void updateStatus(Long id) {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isEmpty()) {
            throw new EntityNotFoundException();
        }

        payment.get().setStatus(Status.APPROVED_NO_INTEGRATION);
        repository.save(payment.get());
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
