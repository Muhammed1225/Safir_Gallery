insert into authority_list
(role, admin)
values
('ROLE_ADMIN',true);

insert into users
(username,password,enabled)
values
('admin1','{noop}1225',1);

insert into authorities (username,authority) select 'admin1',role from authority_list where admin=true;

insert into admins
(name,username)
values
('Muhammed','admin1');