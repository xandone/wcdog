```
### JaveWeb小项目
基于SSM架构，包含1.移动端app(Android) 2.web前端项目 3.后台管理系统<br/>
戳这里[web前端项目](https://github.com/xandone/wcdog-web)
戳这里[后台管理系统](https://github.com/xandone/wcdog-manager)


## TO-DO
#### 移动端app(Android)
- [x] 登录/注册
- [x] 发帖
- [x] 回复/点赞
- [x] 搜索功能
- [x] 个人中心
- [ ] 其他

#### web前端项目
- [x] 登录/注册
- [x] 发帖
- [x] 回复/点赞
- [x] 搜索功能
- [x] 个人中心
- [ ] 其他

#### 管理后台
- [x] 用户管理
- [x] 帖子管理
- [x] 评论管理
- [ ] 图片管理
- [ ] 用户权限
- [ ] 管理员权限
- [x] banner管理
- [ ] 其他

### 项目部署
#### 版本
**tomcat**   7.0.91 <br/>
**maven**    3.5.4 <br/>
**jdk**      1.8.0 <br/>
**mysql**    5.7.19 <br/>
**spring**   4.1.3 <br/>
**mybatis**  3.2.8 <br/>
**os**       CentOs 6.5 <br/>
**nginx**    1.12.2 <br/>

### db
## User表
create table y_user(
id int(11) unsigned not null auto_increment,
name varchar(20) not null unique,
password varchar(20) not null,
nickname varchar(20) not null,
user_id varchar(18) DEFAULT NULL,
user_icon varchar(255) DEFAULT NULL,
talk varchar(300),
address varchar(100),
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
approval_time datetime DEFAULT NULL,
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

## 流量统计表
create table y_wcdog_flow(
id int(11) unsigned not null auto_increment,
classic_count int(5) DEFAULT '0',
yellow_count int(5) DEFAULT '0',
mind_count int(5) DEFAULT '0',
shite_count int(5) DEFAULT '0',
cold_count int(5) DEFAULT '0',
post_time datetime DEFAULT NULL,
primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## Admin表
create table y_admin(
id int(11) unsigned not null auto_increment,
name varchar(20) not null unique,
password varchar(20) not null,
nickname varchar(20) not null,
admin_id varchar(18) DEFAULT NULL,
admin_icon varchar(255) DEFAULT NULL,
permisson varchar(255) DEFAULT NULL,
token varchar(100),
regist_time datetime,
last_login_time datetime,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

```