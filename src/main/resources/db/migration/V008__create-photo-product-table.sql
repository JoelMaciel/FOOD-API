create table photo_product (
    product_id bigint not null,
    file_name varchar(150) not null,
    description varchar(150) ,
    content_type varchar(80) not null,
    file_size int not null,

    primary key (product_id),
    constraint fk_photo_product_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;