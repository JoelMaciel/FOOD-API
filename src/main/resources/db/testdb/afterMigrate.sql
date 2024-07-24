set foreign_key_checks = 0;

DELETE FROM city;
DELETE FROM group_permission;
DELETE FROM `group`;
DELETE FROM kitchen;
DELETE FROM payment_method;
DELETE FROM permission;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM restaurant_payment_method;
DELETE FROM state;
DELETE FROM `user`;
DELETE FROM user_group;

set foreign_key_checks = 1;

ALTER TABLE city AUTO_INCREMENT = 1;
ALTER TABLE `group` AUTO_INCREMENT = 1;
ALTER TABLE kitchen AUTO_INCREMENT = 1;
ALTER TABLE payment_method AUTO_INCREMENT = 1;
ALTER TABLE permission AUTO_INCREMENT = 1;
ALTER TABLE product AUTO_INCREMENT = 1;
ALTER TABLE restaurant AUTO_INCREMENT = 1;
ALTER TABLE state AUTO_INCREMENT = 1;
ALTER TABLE `user` AUTO_INCREMENT = 1;


insert into kitchen (id, name) values (1, 'Thailand');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Japonese');
insert into kitchen (id, name) values (4, 'Brazilian');
insert into kitchen (id, name) values (5, 'Argentina');


insert into state (id, name) values (1, 'Califórnia');
insert into state (id, name) values (2, 'Texas');
insert into state (id, name) values (3, 'New York');

insert into city (id, name, state_id) values (1, 'Nova York', 1);
insert into city (id, name, state_id) values (2, 'Los Angeles', 1);
insert into city (id, name, state_id) values (3, 'Chicago', 2);


insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date, active, address_city_id, address_zip_code, address_street, address_number, address_district) values (1, 'Sushi Sensation', 10, 1, utc_timestamp, utc_timestamp, true, 1, '38400-999', 'Main Street', '1000', 'Downtown');
insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date, active)values ('Veggie Delight', 8.50, 1, utc_timestamp, utc_timestamp, true);
insert into restaurant (name, freight_rate, kitchen_id, registration_date, update_date, active) values ('Seafood Shack', 13, 2, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date, active) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date, active) values (5, 'Snack Bar Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, freight_rate, kitchen_id, registration_date, update_date, active) values (6, 'Cajun e Creole', 6, 4, utc_timestamp, utc_timestamp, true);

insert into payment_method (id, description) values (1, 'Credit card');
insert into payment_method (id, description) values (2, 'Debit card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'VIEW_KITCHENS', 'Allows viewing kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into product (name, description, price, active, restaurant_id) values ('Sweet and Sour Pork', 'Delicious pork with special sauce', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Thai Shrimp', '16 large shrimp with spicy sauce', 110, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Spicy Salad with Grilled Beef', 'Leaf salad with thin slices of grilled beef and our special red pepper sauce', 87.20, 1, 2);
insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Traditional Indian bread with garlic topping', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Chicken cubes prepared with curry sauce and spices', 43, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Ancho Steak', 'Soft and juicy cut, two fingers thick, taken from the front part of the strip loin', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Very tasty cut, with a T-shaped bone, with strip loin on one side and fillet mignon on the other', 89, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('X-all Sandwich', 'Big sandwich with lots of cheese, beef hamburger, bacon, egg, salad, and mayonnaise', 19, 1, 5);
insert into product (name, description, price, active, restaurant_id) values ('Cupim Skewer', 'Served with flour, cassava, and vinaigrette', 8, 1, 6);

insert into `group` (name) values ('Manager'), ('Salesperson'), ('Secretary'), ('Register');
