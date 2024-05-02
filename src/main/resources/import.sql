

insert into kitchen (id, name) values (1, 'Thailand');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Japonese');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);


insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date, address_city_id, address_zip_code, address_street, address_number, address_district) values (1, 'Sushi Sensation', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Main Street', '1000', 'Downtown');

insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date)values ('Veggie Delight', 8.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date) values ('Seafood Shack', 13, 2, utc_timestamp, utc_timestamp);

insert into payment_method (id, description) values (1, 'Credit card');
insert into payment_method (id, description) values (2, 'Debit card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'VIEW_KITCHENS', 'Allows viewing kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

