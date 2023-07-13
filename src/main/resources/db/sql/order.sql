CREATE TABLE orders
(
    id           DECIMAL,
    customerName VARCHAR(500),
    productName  VARCHAR(500),
    quantity     INT,
    price        DECIMAL,
    status       VARCHAR(500),
    PRIMARY KEY (id)
);
