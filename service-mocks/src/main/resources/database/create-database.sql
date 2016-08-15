create table user (
  id int not null auto_increment,
  name varchar(100) not null,
  email varchar(100) not null,
  birth date,
  primary key (id)
);

create table event (
  id int not null auto_increment,
  name varchar(100) not null,
  rating int,
  price double,
  primary key (id)
);

create table assignment (
  id int not null auto_increment,
  event_id int,
  auditorium_id int,
  assignment_datetime datetime,
  primary key (id)
);

create table ticket (
  id int not null auto_increment,
  event_id int,
  auditorium_id int,
  user_id int,
  strategy_id int,
  seat int,
  ticket_datetime datetime,
  price_base double,
  price_rait double,
  price_vip double,
  discount double,
  price double,
  primary key (id)
);

create table event_counter (
  id int not null auto_increment,
  event_id int,
  by_name_count int,
  price_count int,
  book_count int,
  primary key (id)
);

create table discount_counter (
  id int not null auto_increment,
  user_id int,
  discount_id int,
  count int,
  primary key (id)
);

