create table categories (id bigserial primary key, title varchar(255));
insert into categories (title)
values ('Food');

create table products (id bigserial primary key, producttitle varchar(255), productprice int, category_id bigint references categories (id));
insert into products (producttitle, productprice, category_id)
values
('Milk', 85, 1),
('Bread', 25, 1),
('Cheese', 450,1 );