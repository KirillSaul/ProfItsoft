CREATE TABLE categories
(
    id   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE products
(
    id          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(45) NOT NULL,
    category_id BIGINT      NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE
);