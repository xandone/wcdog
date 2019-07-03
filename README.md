
### JaveWeb迷你新闻发布系统
基于SSM架构迷你新闻发布系统，包含前端、后端、移动端三端应用。
### 介绍
#### 1.移动端app(Android) 
MVP+Material+retrofit2+Rx..<br/>
戳这里[移动端app(Android)](https://github.com/xandone/wcdog-app)<br/>
#### 2.web前端项目 
vue+vuex+vue Router+scss<br/>
戳这里[web前端项目](https://github.com/xandone/wcdog-web)<br/>
#### 3.后台管理系统
vue+vuex+vue Router+element<br/>
戳这里[后台管理系统](https://github.com/xandone/wcdog-manager)

## TO-DO
#### 移动端app(Android)
- [x] 登录/注册
- [ ] 发帖
- [x] 回复/点赞
- [x] 搜索功能
- [x] 个人中心
- [ ] 收藏
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
**IED**   IntelliJ IDEA <br/>
**tomcat**   7.0.91 <br/>
**maven**    3.5.4 <br/>
**jdk**      1.8.0 <br/>
**mysql**    5.7.19 <br/>
**spring**   4.1.3 <br/>
**mybatis**  3.2.8 <br/>
**os**       CentOs 6.5 <br/>
**nginx**    1.12.2 <br/>

### 说明
1.mysql数据库，见项目根目录wcdog.sql，需手动导入，可自行添加数据<br/>
2.war包见项目根目录，wcdog.war，注意tomcat端口，管理系统和H5前端baseUrl均为8081端口，
也可以自行修改。

#### 效果图预览
详见各端项目README文件预览图


### db
```
## User表
create table y_user(
id int(11) unsigned not null auto_increment,
user_id varchar(18) not null,
name varchar(20) not null unique,
password varchar(20) not null,
nickname varchar(20) not null,
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
admin_id varchar(18) not null,
admin_icon varchar(255) DEFAULT NULL,
permisson varchar(255) DEFAULT NULL,
token varchar(100),
regist_time datetime,
last_login_time datetime,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## 说说列表
create table y_talk_list(
id int(11) unsigned not null auto_increment,
talk_id varchar(18) not null,
user_id varchar(18) DEFAULT NULL,
talk varchar(100),
send_time datetime,
token varchar(100),
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## 公告栏表
create table y_plank(
id int(11) unsigned not null auto_increment,
plank_id varchar(18) not null,
content varchar(300) DEFAULT NULL,
send_time datetime,
token varchar(100),
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## apk版本
create table y_apk(
id int(11) unsigned not null auto_increment,
apk_id varchar(18) not null,
apk_version varchar(20)not null,
apk_code int(5)not null,
content varchar(300) not null,
send_time datetime,
token varchar(100),
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```