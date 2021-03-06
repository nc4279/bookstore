create table book (
    id SERIAL,
    author varchar(255) not null,
    title varchar(255) not null,
    year int4 not null,
    primary key (id)
);

create table bookstore (
    id SERIAL,
    address varchar(255) not null,
    name varchar(255) not null,
    owner varchar(255) not null,
    primary key (id)
);

create table copy (
    book_id SERIAL,
    bookstore_id SERIAL,
    copies int4 not null,
    soldcopies int4 not null,
    primary key (book_id, bookstore_id)
);

create sequence hibernate_sequence start 1 increment 1;

alter table
    if exists copy
add
    constraint FKof5k7k6c41i06j6fj3slgsmam foreign key (book_id) references book;

alter table
    if exists copy
add
    constraint FKlhrhud2ihjmmkhecwiqgrv3dt foreign key (bookstore_id) references bookstore;