INSERT INTO products(id, price, name, description)
VALUES (1, 450.0, 'Rose', 'цветок'),
       (2, 4500.0, 'Tree','дерево'),
       (3, 20, 'Apple','фрукт');

alter sequence product_seq restart with 4;