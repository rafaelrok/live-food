package br.com.livefood.payments.service;

import br.com.livefood.payments.domain.dto.PaymentDTO;
import br.com.livefood.payments.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

/**
 * @author rafae
 */


@Service
public class PaymentService {

    @Autowired
    private PaymentServiceImpl service;


    public Page<PaymentDTO> findAllPayment(Pageable pageable) {
        return service.findAllPayment(pageable);
    }

    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        return service.savePayment(paymentDTO);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        return service.updatePayment(id, paymentDTO);
    }

    public PaymentDTO findPaymentById(Long id) {
        return service.findPaymentById(id);
    }

    public void confirmPayment(Long id) {
        service.confirmPayment(id);
    }

    public void updateStatus(Long id) {
        service.updateStatus(id);
    }

    public void deletePayment(Long id) {
        service.deletePayment(id);
    }

}
