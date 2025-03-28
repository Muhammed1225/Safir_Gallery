insert into authority_list
(role, admin)
values
('ROLE_ADMIN',true);

insert into users
(username,password,enabled)
values
('Codeadam','{noop}345',1),
('admin1','{noop}1225',1);

insert into authorities (username,authority) select 'admin1',role from authority_list where admin=true;
insert into authorities (username,authority) select 'Codeadam',role from authority_list where admin=true;

insert into admins
(name,username)
values
('Ibrahim','Codeadam'),
('Muhammed','admin1');

insert into categories
(name)
values
('Category1'),
('Category2'),
('Category3');

insert into flowers
(text, price, images, category_id)
values
('flower1', 21, 'p1', '2'),
('flower2', 45, 'p2', '1'),
('flower3', 34, 'p3', '3');