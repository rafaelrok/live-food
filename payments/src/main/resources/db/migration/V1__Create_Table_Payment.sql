CREATE TABLE payments
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    amount            DECIMAL(19,2)               NOT NULL,
    name              VARCHAR(100)          NULL,
    number            VARCHAR(19)           NULL,
    expiration        VARCHAR(7)            NULL,
    code              VARCHAR(3)            NULL,
    status            VARCHAR(255)          NOT NULL,
    order_id          BIGINT                NOT NULL,
    payment_mathod_id BIGINT                NOT NULL,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);