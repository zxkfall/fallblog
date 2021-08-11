# 创建数据库
drop database if exists fall_blog;
create database if not exists fall_blog default character set utf8;
use fall_blog;
# 创建用户权限相关表
drop table if exists t_user;
create table if not exists t_user
(
    id          varchar(40) primary key not null,
    nickname    varchar(50)             not null,
    email       varchar(40)             not null,
    password    varchar(130)            not null,
    avatar      varchar(512)                     default null,
    description varchar(512)                     default null,
    status      int                     not null default 1,
    create_time datetime                not null,
    update_time datetime                not null
) engine = INNODB
  character set = utf8;


drop table if exists t_user_extends;
create table if not exists t_user_extends
(
    id          varchar(40) primary key not null,
    user_id     varchar(40)             not null,
    gender      int                     not null default 3, # 0 男 1 女  2 不确定 3 未设定
    birth       datetime                         default null,
    hobby       varchar(128)                     default null,
    address     varchar(512)                     default null,
    field       varchar(512)                     default null,
    update_time datetime                not null
) engine = INNODB
  character set utf8;


drop table if exists t_role;
create table if not exists t_role
(
    id          int primary key not null auto_increment,
    name        varchar(20)     not null,
    description varchar(128) default null,
    create_time datetime,
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_role_user;
create table if not exists t_role_user
(
    id          varchar(40) primary key not null,
    user_id     varchar(40),
    role_id     int,
    create_time datetime,
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_permission;
create table if not exists t_permission
(
    id          int primary key not null auto_increment,
    name        varchar(64),
    path        varchar(512),
    description varchar(512),
    create_time datetime,
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_role_permission;
create table if not exists t_role_permission
(
    id            int primary key not null auto_increment,
    role_id       long,
    permission_id long,
    create_time   datetime,
    update_time   datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_category;
create table if not exists t_category
(
    id                int primary key not null auto_increment,
    name              varchar(64),
    description       varchar(512),
    last_edit_user_id varchar(40),
    create_time       datetime,
    update_time       datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_article;
create table if not exists t_article
(
    id          varchar(40) primary key not null,
    title       varchar(128),
    user_id     varchar(40),
    description varchar(256),
    first_image varchar(512),
    category_id int,
    tags        varchar(512),
    content     text,
    status      int,
    create_time datetime,
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_article_views;
create table if not exists t_article_views
(
    article_id varchar(40) primary key not null,
    views      bigint
) engine = INNODB
  character set = utf8;


drop table if exists t_article_stars;
create table if not exists t_article_stars
(
    id          varchar(40) primary key not null,
    article_id  varchar(40)             not null,
    user_id     varchar(40)             not null,
    update_time datetime
) engine = INNODB
  character set = utf8;


drop table if exists t_article_looking;
create table if not exists t_article_looking
(
    id          varchar(40) primary key not null,
    article_id  varchar(40)             not null,
    user_id     varchar(40),
    ip          varchar(64),
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_comment;
create table if not exists t_comment
(
    id          varchar(40) primary key not null,
    article_id  varchar(40)             not null,
    user_id     varchar(40),
    email       varchar(128)            not null,
    content     varchar(1024),
    target      varchar(128) default null,# 为空表示为一级评论，不为空根据id或者email排列到对应的一级评论下面，作为二级评论
    status      int,
    create_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_comment_stars;
create table t_comment_stars
(
    id          varchar(40) primary key not null,
    comment_id  varchar(40),
    user_id     varchar(40),
    email       varchar(128)            not null,
    ip          varchar(128),
    update_time datetime
) engine = INNODB
  character set = utf8;


drop table if exists t_tags;
create table if not exists t_tags
(
    id          bigint primary key not null auto_increment,
    name        varchar(128),
    create_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_images;
create table if not exists t_images
(
    id          varchar(40) primary key not null,
    url         varchar(512),
    path        varchar(512),
    name        varchar(128),
    create_time datetime
) engine = INNODB
  character set = utf8;


drop table if exists t_view_count;
create table if not exists t_view_count
(
    count bigint
) engine = INNODB
  character set = utf8;

drop table if exists t_web_view_people;
create table if not exists t_web_view_people
(
    id          varchar(40) primary key not null,
    ip          varchar(128),
    device      varchar(512),
    create_time datetime,
    update_time datetime
) engine = INNODB
  character set = utf8;

drop table if exists t_web_info;
create table if not exists t_web_info
(
    info_key varchar(512)
) engine = INNODB
  character set = utf8;