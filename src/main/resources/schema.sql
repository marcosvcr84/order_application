CREATE DATABASE orders_db;

USE orders_db;

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    control_number BIGINT NOT NULL UNIQUE,
    registration_date DATE NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    client_id INT NOT NULL
);
