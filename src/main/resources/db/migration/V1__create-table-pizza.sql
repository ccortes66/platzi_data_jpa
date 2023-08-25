CREATE TABLE pizza
(
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(5,2) NOT NULL,
  vegetarian TINYINT(2),
  vegan TINYINT(2),
  available TINYINT(2) NOT NULL DEFAULT 1,
  PRIMARY KEY(id),
  UNIQUE(name),
  INDEX(name)
)
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;