CREATE DATABASE IF NOT EXISTS auth;

create table if not exists customer (
  id bigint not null,
  email varchar(255),
  password varchar(255),
  role tinyint check (role between 0 and 2),
  primary key (id)
)