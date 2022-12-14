CREATE TABLE customer
(
    customer_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    customer_name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE customer_product
(
    customer_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_customer_product PRIMARY KEY (customer_id, product_id)
);

CREATE TABLE product
(
    product_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    product_name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE customer_product
    ADD CONSTRAINT FK_CUSTOMER_PRODUCT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (customer_id);

ALTER TABLE customer_product
    ADD CONSTRAINT FK_CUSTOMER_PRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);