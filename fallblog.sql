# # 创建数据库
# drop database if exists fall_blog;
# create database if not exists fall_blog default character set utf8;
# use fall_blog;
# # 创建用户权限相关表
# drop table if exists t_user;
# create table if not exists t_user
# (
#     id          varchar(40) primary key not null,
#     nickname    varchar(50)             not null,
#     email       varchar(40)             not null,
#     password    varchar(130)            not null,
#     avatar      varchar(512)                     default null,
#     description varchar(512)                     default null,
#     status      int                     not null default 1,
#     create_time datetime                not null,
#     update_time datetime                not null
# ) engine = INNODB
#   character set = utf8;
#
#
# drop table if exists t_user_extends;
# create table if not exists t_user_extends
# (
#     id          varchar(40) primary key not null,
#     user_id     varchar(40)             not null,
#     gender      int                     not null default 3, # 0 男 1 女  2 不确定 3 未设定
#     birth       datetime                         default null,
#     hobby       varchar(128)                     default null,
#     address     varchar(512)                     default null,
#     field       varchar(512)                     default null,
#     update_time datetime                not null
# ) engine = INNODB
#   character set utf8;
#
#
# drop table if exists t_role;
# create table if not exists t_role
# (
#     id          int primary key not null auto_increment,
#     name        varchar(20)     not null,
#     description varchar(128) default null,
#     create_time datetime,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_role_user;
# create table if not exists t_role_user
# (
#     id          varchar(40) primary key not null,
#     user_id     varchar(40),
#     role_id     int,
#     create_time datetime,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_permission;
# create table if not exists t_permission
# (
#     id          int primary key not null auto_increment,
#     name        varchar(64),
#     path        varchar(512),
#     description varchar(512),
#     create_time datetime,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_role_permission;
# create table if not exists t_role_permission
# (
#     id            int primary key not null auto_increment,
#     role_id       int,
#     permission_id int,
#     create_time   datetime,
#     update_time   datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_category;
# create table if not exists t_category
# (
#     id                int primary key not null auto_increment,
#     name              varchar(64),
#     description       varchar(512),
#     last_edit_user_id varchar(40),
#     create_time       datetime,
#     update_time       datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_article;
# create table if not exists t_article
# (
#     id          varchar(40) primary key not null,
#     title       varchar(128),
#     user_id     varchar(40),
#     description varchar(256),
#     first_image varchar(512),
#     category_id int,
#     tags        varchar(512),
#     content     text,
#     status      int,
#     create_time datetime,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_article_views;
# create table if not exists t_article_views
# (
#     article_id varchar(40) primary key not null,
#     views      bigint
# ) engine = INNODB
#   character set = utf8;
#
#
# drop table if exists t_article_stars;
# create table if not exists t_article_stars
# (
#     id          varchar(40) primary key not null,
#     article_id  varchar(40)             not null,
#     user_id     varchar(40)             not null,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
#
# drop table if exists t_article_looking;
# create table if not exists t_article_looking
# (
#     id          varchar(40) primary key not null,
#     article_id  varchar(40)             not null,
#     user_id     varchar(40),
#     ip          varchar(64),
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_comment;
# create table if not exists t_comment
# (
#     id          varchar(40) primary key not null,
#     article_id  varchar(40)             not null,
#     user_id     varchar(40),
#     email       varchar(128)            not null,
#     content     varchar(1024),
#     target      varchar(128) default null,# 为空表示为一级评论，不为空根据id或者email排列到对应的一级评论下面，作为二级评论
#     status      int,
#     create_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_comment_stars;
# create table t_comment_stars
# (
#     id          varchar(40) primary key not null,
#     comment_id  varchar(40),
#     user_id     varchar(40),
#     email       varchar(128)            not null,
#     ip          varchar(128),
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
#
# drop table if exists t_tags;
# create table if not exists t_tags
# (
#     id          bigint primary key not null auto_increment,
#     name        varchar(128),
#     create_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_images;
# create table if not exists t_images
# (
#     id          varchar(40) primary key not null,
#     url         varchar(512),
#     path        varchar(512),
#     name        varchar(128),
#     create_time datetime
# ) engine = INNODB
#   character set = utf8;
#
#
# drop table if exists t_web_view_count;
# create table if not exists t_web_view_count
# (
#     count bigint
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_web_view_people;
# create table if not exists t_web_view_people
# (
#     id          varchar(40) primary key not null,
#     ip          varchar(128),
#     device      varchar(512),
#     create_time datetime,
#     update_time datetime
# ) engine = INNODB
#   character set = utf8;
#
# drop table if exists t_web_info;
# create table if not exists t_web_info
# (
#     info_key varchar(512)
# ) engine = INNODB
#   character set = utf8;

-- 第一版数据库
-- 创建数据库
drop database if exists fall_blog;
create database if not exists fall_blog default character set utf8;
use fall_blog;
-- 创建用户权限相关表
drop table if exists t_user;
create table if not exists t_user
(
    id          varchar(50)  not null,
    nickname    varchar(50)  not null,
    email       varchar(128) not null,
    password    varchar(130) not null,
    avatar      varchar(512)          default null,
    description varchar(512)          default null,
    status      int          not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint       not null,
    create_time datetime     not null,
    update_time datetime     not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

-- 最初的用户 {bcrypt}加密 密码为123456
insert into t_user (id, nickname, email, password, version, create_time, update_time)
values ('1', 'zxkfall', '1475795322@qq.com', '$2a$10$QeOl0J8ZiiGWv7W4znoleOQMceQEkz9aC4pkHXFAEzek1R1Ctnn/m', 1,
        curdate(), curdate());

drop table if exists t_user_extends;
create table if not exists t_user_extends
(
    id          varchar(50) not null,
    user_id     varchar(50) not null,
    gender      int         not null default 3, -- 0 男 1 女  2 不确定 3 未设定
    birth       datetime             default null,
    hobby       varchar(128)         default null,
    address     varchar(512)         default null,
    field       varchar(512)         default null,
    status      int         not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint      not null,
    create_time datetime    not null,
    update_time datetime    not null,
    primary key (`id`)
) engine = INNODB
  character set utf8;


drop table if exists t_authorities;
create table if not exists t_authorities
(
    id          int         not null auto_increment,
    email       varchar(50) not null,
    authority   varchar(50) not null,
    description varchar(128)         default null,
    status      int         not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint      not null,
    create_time datetime    not null,
    update_time datetime    not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

insert into t_authorities (email, authority,version,create_time,update_time)
values ('zxkfall', 'ROLE_ADMIN',1,curdate(),curdate());

drop table if exists t_category;
create table if not exists t_category
(
    id          int      not null auto_increment,
    name        varchar(64),
    description varchar(512),
    status      int      not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint   not null,
    create_time datetime not null,
    update_time datetime not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

drop table if exists t_article;
create table if not exists t_article
(
    id          varchar(50)  not null,
    title       varchar(128) not null,
    user_id     varchar(50)  not null,
    description varchar(256)          default null,
    first_image varchar(512)          default null,
    category_id int          not null,
    tags        varchar(1024)         default null,
    content     text         not null,
    status      int          not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint       not null,
    create_time datetime     not null,
    update_time datetime     not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

drop table if exists t_article_views;
create table if not exists t_article_views
(
    id          bigint      not null,
    article_id  varchar(50) not null,
    view_count  bigint,
    status      int         not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint      not null,
    create_time datetime    not null,
    update_time datetime    not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;


drop table if exists t_article_stars;
create table if not exists t_article_stars
(
    id          varchar(50)  not null,
    article_id  varchar(50)  not null,
    ip          varchar(64)  not null,
    device      varchar(128) not null,
    status      int          not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint       not null,
    create_time datetime     not null,
    update_time datetime     not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;



drop table if exists t_comment;
create table if not exists t_comment
(
    id          varchar(40)   not null,
    article_id  varchar(40)   not null,
    nickname    varchar(128)           default null,
    email       varchar(128)  not null,
    content     varchar(1024) not null,
    target      varchar(128)           default null,# 为空表示为一级评论，不为空根据id或者email排列到对应的一级评论下面，作为二级评论
    ip          varchar(64)   not null,
    device      varchar(128)  not null,
    status      int           not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint        not null,
    create_time datetime      not null,
    update_time datetime      not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

drop table if exists t_comment_stars;
create table t_comment_stars
(
    id          varchar(40)  not null,
    comment_id  varchar(40)  not null,
    email       varchar(128) not null,
    ip          varchar(128) not null,
    status      int          not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint       not null,
    create_time datetime     not null,
    update_time datetime     not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;


drop table if exists t_tag;
create table if not exists t_tags
(
    id          bigint       not null auto_increment,
    name        varchar(128) not null,
    status      int          not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint       not null,
    create_time datetime     not null,
    update_time datetime     not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

drop table if exists t_image;
create table if not exists t_images
(
    id          varchar(50) not null,
    url         varchar(512),
    path        varchar(512),
    name        varchar(128),
    type        varchar(40),
    status      int         not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint      not null,
    create_time datetime    not null,
    update_time datetime    not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;



drop table if exists t_web_view_people;
create table if not exists t_web_view_people
(
    id          varchar(50) not null,
    ip          varchar(128),
    device      varchar(512),
    status      int         not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint      not null,
    create_time datetime    not null,
    update_time datetime    not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;

drop table if exists t_web_info;
-- view_count
create table if not exists t_web_info
(
    id          varchar(50)   not null,
    info_key    varchar(512)  not null,
    info_value  varchar(1024) not null,
    status      int           not null default 1 comment '1状态,1为可用,0为不可用',
    version     bigint        not null,
    create_time datetime      not null,
    update_time datetime      not null,
    primary key (`id`)
) engine = INNODB
  character set = utf8;