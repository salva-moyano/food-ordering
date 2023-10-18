SET MODE MYSQL;

CREATE TABLE orders (
    id VARCHAR(150) NOT NULL,
    customer_id VARCHAR(150) NOT NULL,
    restaurant_id VARCHAR(150) NOT NULL,
    tracking_id VARCHAR(150) NOT NULL,
    address_id VARCHAR(150) NOT NULL,
    price DECIMAL NOT NULL,
    order_status VARCHAR(100) NOT NULL,
    failure_messages VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_items (
    id BIGINT NOT NULL,
    order_id VARCHAR(150) NOT NULL,
    product_id VARCHAR(150) NOT NULL,
    price DECIMAL NOT NULL,
    quantity INT NOT NULL,
    sub_total DECIMAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customers (
    id VARCHAR(150) NOT NULL,
    username VARCHAR(150) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE restaurant (
    id VARCHAR(150) NOT NULL,
    product_id VARCHAR(150) NOT NULL,
    name VARCHAR(150) NOT NULL,
    active TINYINT NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    product_price DECIMAL NOT NULL,
    product_available TINYINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE payment_outbox (
    id VARCHAR(150) NOT NULL,
    saga_id VARCHAR(150) NOT NULL,
    created_at DATETIME NOT NULL,
    processed_at DATETIME NOT NULL,
    type VARCHAR(150) NOT NULL,
    payload VARCHAR(255) NOT NULL,
    saga_status VARCHAR(100) NOT NULL,
    order_status VARCHAR(100) NOT NULL,
    outbox_status VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE restaurant_approval_outbox (
    id VARCHAR(150) NOT NULL,
    saga_id VARCHAR(150) NOT NULL,
    created_at DATETIME NOT NULL,
    processed_at DATETIME NOT NULL,
    type VARCHAR(150) NOT NULL,
    payload VARCHAR(255) NOT NULL,
    saga_status VARCHAR(100) NOT NULL,
    order_status VARCHAR(100) NOT NULL,
    outbox_status VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);