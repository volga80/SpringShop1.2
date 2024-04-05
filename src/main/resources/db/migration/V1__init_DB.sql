--users
DROP SEQUENCE IF EXISTS user_seq;

create sequence user_seq start 1 increment 1;

DROP table if exists users cascade;
create table users (
    id      BIGINT  not null,
    email   varchar(255),
    name    varchar(255),
    password varchar(255),
    role    varchar(255),
    bucket_id int8,
    primary key (id)
);

--buckets
DROP SEQUENCE IF EXISTS bucket_seq;

CREATE SEQUENCE bucket_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS buckets CASCADE;

CREATE TABLE buckets (
    id      BIGINT NOT NULL,
    user_id BIGINT UNIQUE,
    PRIMARY KEY (id)
);

-- соединение users и buckets
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;

alter table if exists users
    add constraint users_fk_user
        foreign key (bucket_id) references buckets;

--categories
DROP SEQUENCE IF EXISTS category_seq;

CREATE SEQUENCE category_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
    id    BIGINT NOT NULL,
    title VARCHAR(255),
    PRIMARY KEY (id)
);

-- product
DROP SEQUENCE IF EXISTS product_seq;

CREATE SEQUENCE product_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (
    amount      INTEGER NOT NULL,
    price       NUMERIC(38,2),
    id          BIGINT NOT NULL,
    description VARCHAR(255),
    name        VARCHAR(255),
    period      VARCHAR(255),
    PRIMARY KEY (id)
);

--соединение product and categories
DROP TABLE IF EXISTS products_categories;

CREATE TABLE products_categories (
    category_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL
);

alter table if exists products_categories
    add constraint products_categories_fk_category
        foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint product_categories_fk_products
        foreign key (product_id) references products;

--продукты в корзине
DROP TABLE IF EXISTS buckets_products;

CREATE TABLE buckets_products (
    bucket_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL
);
alter table if exists buckets_products
    add constraint buckets_products_fk_products
        foreign key (product_id) references products;
alter table if exists buckets_products
    add constraint bucket_products_fk_bucket
        foreign key (bucket_id) references buckets;

-- orders
DROP SEQUENCE IF EXISTS order_seq;

CREATE SEQUENCE order_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders (
    status   SMALLINT CHECK (status BETWEEN 0 AND 4),
    sum      NUMERIC(38,2),
    created  TIMESTAMP(6),
    id       BIGINT NOT NULL,
    updated  TIMESTAMP(6),
    user_id  BIGINT,
    address  VARCHAR(255),
    PRIMARY KEY (id)
);

alter table if exists orders
    add constraint orders_fk_users
        foreign key (user_id) references users;

--order_details
DROP SEQUENCE IF EXISTS order_details_seq;

CREATE SEQUENCE order_details_seq START 1 INCREMENT 1;

DROP TABLE IF EXISTS orders_details;

CREATE TABLE orders_details (
    amount      NUMERIC(38,2),
    price       NUMERIC(38,2),
    details_id  BIGINT NOT NULL UNIQUE,
    id          BIGINT NOT NULL,
    order_id    BIGINT,
    product_id  BIGINT,
    commentary  VARCHAR(255),
    PRIMARY KEY (id)
);
alter table if exists orders_details
    add constraint orders_details_fk_order
        foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_products
        foreign key (product_id) references products;

alter table if exists orders_details
    add constraint orders_details_fk_orders_details
        foreign key (details_id) references orders_details;

--соединение users и orders
drop table if exists users_orders_list cascade;

create table users_orders_list (
orders_list_id bigint   not null unique,
user_id bigint          not null
);

alter table if exists users_orders_list
    add constraint  users_orders_list_fk_orders
        foreign key (orders_list_id) references orders;
alter table if exists users_orders_list
    add constraint  users_orders_list_fk_users
        foreign key (user_id) references users;

--соединение orders и products
drop table if exists orders_products cascade;

create table orders_products (
order_id bigint     not null,
product_id bigint   not null
);

alter table if exists orders_products
    add constraint orders_products_fk_products
        foreign key (product_id) references products;
alter table if exists orders_products
    add constraint orders_product_fk_orders
        foreign key (order_id) references orders;