package br.com.livefood.payments.service.impl;

import br.com.livefood.payments.domain.dto.PaymentDTO;
import br.com.livefood.payments.domain.entity.Payment;
import br.com.livefood.payments.domain.entity.Status;
import br.com.livefood.payments.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


/**
 * @author rafae
 */

@Component
public class PaymentServiceImpl {

    @Autowired
    private PaymentRepository repository;

    private ModelMapper modelMapper;

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
        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
