CREATE TABLE pizza_order
(
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  customer_id BIGINT(20) UNSIGNED NOT NULL,
  date DATETIME DEFAULT CURRENT_TIMESTAMP,
  total DECIMAL(5,2) NOT NULL,
  method VARCHAR(1) NOT NULL,
  additional_notes VARCHAR(255),
  PRIMARY KEY(id)

)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;