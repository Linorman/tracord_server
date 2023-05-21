drop database if exists tracord;
create database tracord;
use tracord;

drop table if exists tb_user;
create table tracord.tb_user
(
    id          int auto_increment
        primary key,
    account     varchar(50)   not null,
    nickname    varchar(50)   null,
    avatar      varchar(50)   null,
    gender      int           null comment '0-96表示97种性别',
    password    varchar(255)  not null,
    age         int           null,
    create_date datetime      null,
    update_date datetime      null,
    del_flag    int default 0 not null,
    constraint tb_user_account_uindex
        unique (account)
);
drop table if exists tb_passage_follower;
create table tracord.tb_passage_follower
(
    passage_id  int           not null,
    id          int auto_increment
        primary key,
    follower_id int           not null,
    del_flag    int default 0 not null,
    create_date datetime      null
);
drop table if exists tb_passage;
create table tracord.tb_passage
(
    id           int auto_increment
        primary key,
    user_id      int           not null,
    content      varchar(255)  null,
    image        varchar(255)  null,
    follower_num int default 0 null,
    address      varchar(50)   null,
    create_date  datetime      null,
    del_flag     int default 0 not null
);