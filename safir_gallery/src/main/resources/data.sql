insert into authority_list
(role, admin)
values
('ROLE_ADMIN',true);

insert into users
(username,password,enabled)
values
('codeAdam','{noop}Microsoft13',1),
('admin1','{noop}1225',1);

insert into authorities (username,authority) select 'admin1',role from authority_list where admin=true;

insert into admins
(name,username)
values
('Ibrahim','codeAdam'),
('Muhammed','admin1');

insert into categories
(name)
values
('Category1'),
('Category2'),
('Category3');

insert into flowers
(text, images, category_id)
values
('flower1', 'p1', '2'),
('flower2', 'p2', '1'),
('flower3', 'p3', '3');