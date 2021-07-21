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
price int,
category_id bigint references categories(id),
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp
);
insert into products (title, price, category_id)
values
('Product1', 10, 1),
('Product2', 20, 1),
('Product3', 30, 1),
('Product4', 40, 1),
('Product5', 50, 1),
('Product6', 60, 1),
('Product7', 70, 1),
('Product8', 80, 1),
('Product9', 90, 1),
('Product10', 100, 1),
('Product11', 110, 1),
('Product12', 120, 1),
('Product13', 130, 1),
('Product14', 140, 1),
('Product15', 150, 1),
('Product16', 160, 1),
('Product17', 170, 1),
('Product18', 180, 1),
('Product19', 190, 1),
('Product20', 200, 1),
('Product21', 210, 1),
('Product22', 220, 1),
('Product23', 230, 1),
('Product24', 240, 1),
('Product25', 250, 1),
('Product26', 260, 1),
('Product27', 270, 1),
('Product28', 280, 1),
('Product29', 290, 1),
('Product30', 300, 1);