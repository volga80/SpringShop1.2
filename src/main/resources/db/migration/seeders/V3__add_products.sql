INSERT INTO products(id, price, name, description, amount)
VALUES (1, 450.0, 'Rose', 'цветок', 10),
       (2, 4500.0, 'Tree','дерево', 10),
       (3, 20, 'Apple','фрукт', 10);

alter sequence product_seq restart with 4;