create table categories (
id bigserial primary key,
title varchar(255),
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp
);

insert into categories (title) values ('Food');

create table products (
id bigserial primary key,
title varchar(255),
price numeric (8, 2) not null,
category_id bigint references categories(id),
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp
);
insert into products (title, price, category_id)
values
('Product1', 10, 1),
('Product2', 20, 1);
