package br.com.livefood.payments.repository;

import br.com.livefood.payments.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rafae
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
