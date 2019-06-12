```
## User表
create table y_user(
id int(11) unsigned not null auto_increment,
name varchar(20) not null unique,
password varchar(20) not null,
nickname varchar(20) not null,
user_id varchar(18) DEFAULT NULL,
user_icon varchar(255) DEFAULT NULL,
token varchar(100),
regist_time datetime,
last_login_time datetime,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## joke表
create table y_joke(
id int(11) unsigned not null auto_increment,
joke_id varchar(18) not null,
joke_user_id varchar(18) not null,
title varchar(255) not null,
content mediumtext,
contentHtml mediumtext,
cover_img varchar(255) DEFAULT NULL,
post_time datetime DEFAULT NULL,
art_like_count int(5) DEFAULT '0',
art_comment_count int(5) DEFAULT '0',
category varchar(4) DEFAULT NULL,
tags varchar(100) DEFAULT NULL,
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## joke点赞表
create table y_joke_like(
id int(11) unsigned not null auto_increment,
joke_id varchar(18) not null,
joke_user_id varchar(18),
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## joke评论表
create table y_joke_comment(
id int(11) unsigned not null auto_increment,
comment_id varchar(18) not null,
joke_id varchar(18),
comment_user_id varchar(18),
comment_details mediumtext,
comment_date datetime DEFAULT NULL,
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## 图片
create table y_image(
id int(11) unsigned not null auto_increment,
user_id varchar(18) not null,
imgId varchar(18),
imgUrl varchar(255),
pageViews int(6),
size_type int(1),
upTime datetime DEFAULT NULL,
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## 首页轮播
create table y_banner(
id int(11) unsigned not null auto_increment,
user_id varchar(18) not null,
articel_id varchar(18) not null,
title varchar(255),
img_url varchar(255),
article_url varchar(255),
pageViews int(6),
up_time datetime DEFAULT NULL,
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

```