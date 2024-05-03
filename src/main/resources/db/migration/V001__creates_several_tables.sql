create table city (
    id bigint not null auto_increment,
    name varchar(80) not null,
    state_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table group_permission (
    group_id bigint not null,
    permission_id bigint not null
) engine=InnoDB default charset=utf8;


create table `group` (
    id bigint not null auto_increment,
    name varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table kitchen (id bigint not null auto_increment,
     name varchar(80) not null,

     primary key (id)
) engine=InnoDB default charset=utf8;

create table payment_method (
    id bigint not null auto_increment,
    description varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table permission (id bigint not null auto_increment,
    description varchar(200) not null,
    name varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table product (
    id bigint not null auto_increment,
    name varchar(150) not null,
    price decimal(19,2) not null,
    description varchar(200) not null,
    active bit not null,
    restaurant_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table restaurant (
    id bigint not null auto_increment,
    name varchar(60) not null,
    freight_rate decimal(19,2) not null,
    registration_date datetime(6) not null,
    update_date datetime(6) not null,
    kitchen_id bigint not null,

    address_complement varchar(200),
    address_district varchar(100),
    address_number varchar(60),
    address_street varchar(150),
    address_zip_code varchar(60),
    address_city_id bigint,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_method (
    restaurant_id bigint not null,
    payment_method_id bigint not null
) engine=InnoDB default charset=utf8;


create table state (
    id bigint not null auto_increment,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table `user` (
    id bigint not null auto_increment,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    registration_date datetime(6) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table user_group (
    user_id bigint not null,
    group_id bigint not null

) engine=InnoDB default charset=utf8;


alter table city add constraint fk_city_state foreign key (state_id) references state (id);
alter table group_permission add constraint fk_group_permission_permission foreign key (permission_id) references permission (id);
alter table group_permission add constraint fk_group_permission_group foreign key (group_id) references `group` (id);
alter table product add constraint fk_product_restaurant foreign key (restaurant_id) references restaurant (id);
alter table restaurant add constraint fk_restaurant_address foreign key (address_city_id) references city (id);
alter table restaurant add constraint fk_restaurant_kitchen foreign key (kitchen_id) references kitchen (id);
alter table restaurant_payment_method add constraint fk_restaurant_payment_method_payment_method foreign key (payment_method_id) references payment_method (id);
alter table restaurant_payment_method add constraint fk_restaurant_payment_method_restaurant_id foreign key (restaurant_id) references restaurant (id);
alter table user_group add constraint fk_user_group_group foreign key (group_id) references `group` (id);
alter table user_group add constraint fk_user_group_user foreign key (user_id) references `user` (id);
