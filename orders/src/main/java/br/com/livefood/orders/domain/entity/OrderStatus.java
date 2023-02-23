package br.com.livefood.orders.domain.entity;

/**
 * @author rafae
 */

public enum OrderStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    NOT_AUTORIZED,
    DELIVERED;
}
