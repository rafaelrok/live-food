CREATE TABLE orders
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    date_time    datetime              NOT NULL,
    order_status VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);