insert into kitchen (id, name) values (1, 'Thailand');
insert into kitchen (id, name) values (2, 'Indian');

insert into restaurant (name, freight_rate, kitchen_id) values ('Sushi Sensation', 12, 1);
insert into restaurant (name, freight_rate, kitchen_id)values ('Veggie Delight', 8.50, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Seafood Shack', 13, 2);

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into payment_method (id, description) values (1, 'Credit card');
insert into payment_method (id, description) values (2, 'Debit card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'VIEW_KITCHENS', 'Allows viewing kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');

