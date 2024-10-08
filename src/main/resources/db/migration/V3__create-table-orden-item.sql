CREATE TABLE order_item
(
    id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    order_id BIGINT(20) UNSIGNED NOT NULL,
    pizza_id BIGINT(20) UNSIGNED NOT NULL,
    quantity DECIMAL(2,1),
    price DECIMAL(2,1) NOT NULL,
    PRIMARY KEY(id)
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;