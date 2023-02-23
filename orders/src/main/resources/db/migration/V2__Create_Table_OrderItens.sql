CREATE TABLE order_item
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    quantity      VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    order_id      BIGINT                NOT NULL,
    CONSTRAINT pk_order_item PRIMARY KEY (id)
);

ALTER TABLE order_item
    ADD CONSTRAINT FK_ORDER_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);