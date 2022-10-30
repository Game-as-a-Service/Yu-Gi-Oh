drop table if exists member;
create table member
(
    id            serial
        constraint member_id_pk primary key,
    user_id       varchar(255),
    password_hash varchar(255),
    create_date   bigint
);

drop table if exists message;
create table message
(
    id          serial
        constraint message_id_pk primary key,
    user_id     varchar(255),
    action      varchar(255),
    content     varchar(255),
    create_date bigint
);