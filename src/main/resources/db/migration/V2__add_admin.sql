insert into users (id, email, name, password, role, bucket_id)
values (1, 'mail@mail.ru', 'admin', '123', 'ADMIN', null);


alter sequence user_seq restart with 2;