CREATE DATABASE IF NOT EXISTS auth;

create table if not exists customer (
  id bigint auto_increment primary key,
  email varchar(255),
  password varchar(255),
  role enum ('ADMIN','CUSTOMER'),
  constraint UK_email unique (email)
)