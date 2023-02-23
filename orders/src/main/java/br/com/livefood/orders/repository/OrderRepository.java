package br.com.livefood.orders.repository;

import br.com.livefood.orders.domain.entity.Order;
import br.com.livefood.orders.domain.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author rafae
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Order o set o.orderStatus = :status where o = :order")
    void updateStatus(OrderStatus status, Order order);

    @Query("select o from Order o left join fetch o.orderItens where o.id = :id")
    Order findByIdWithOrderItens(Long id);
}
