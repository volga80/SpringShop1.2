insert into users (id, email, name, password, role, bucket_id)
values (1, 'mail@mail.ru', 'admin', '$2a$10$O5N4ZB.HmVE0qmCQc.3Y4emvae6jx3mIg/8Z3ztfWT2N5bdxlq7qq', 'ADMIN', null);


alter sequence user_seq restart with 2;