
### JaveWeb迷你新闻发布系统
基于SSM架构迷你新闻发布系统，包含前端、后端、移动端三端应用。
### 介绍
#### 1.移动端app(Android wcdog-app) 
MVP+Material+retrofit2+Rx..<br/>
戳这里[移动端app(Android)](https://github.com/xandone/wcdog-app)<br/>
#### 2.web前端项目(wcdog-web)
vue+vuex+vue Router+scss<br/>
戳这里[web前端项目](https://github.com/xandone/wcdog-web)<br/>
#### 3.后台管理系统(wcdog-manager)
vue+vuex+vue Router+element<br/>
戳这里[后台管理系统](https://github.com/xandone/wcdog-manager)

## TO-DO
#### 移动端app(Android)
- [x] 登录/注册
- [ ] 发帖
- [x] 回复/点赞
- [x] 搜索功能
- [x] 个人中心
- [x] 版本更新
- [ ] 收藏
- [ ] 其他

#### web前端项目
- [x] 登录/注册
- [x] 发帖
- [x] 回复/点赞
- [x] 搜索
- [x] 公告面板
- [x] 发一条说说
- [x] 个人中心
- [ ] 其他

#### 管理后台
- [x] 用户管理
- [x] 帖子管理
- [x] 评论管理
- [x] 公告面板/说说管理
- [x] banner管理
- [x] 禁言功能
- [ ] 图片管理
- [ ] 用户权限
- [ ] 管理员权限
- [ ] 其他

### 项目部署
#### 版本
**IED**   IntelliJ IDEA <br/>
**tomcat**   7.0.91 <br/>
**maven**    3.5.4 <br/>
**jdk**      1.8.0 <br/>
**mysql**    mysql-8.0.11 <br/>
**spring**   4.1.3 <br/>
**mybatis**  3.2.8 <br/>
**os**       CentOs 6.5 <br/>
**nginx**    1.12.2 <br/>

### 说明
1.mysql数据库，见项目根目录wcdog.sql，需手动导入，可自行添加数据<br/>
2.注意tomcat端口，管理系统和H5前端baseUrl均为8081端口，也可以自行修改。<br/>
3.使用管理后台(wcdog-manager)新增joke的时候，注意使用的是y_user表中的user_id,所以需要
在y_user表中有一个和y_admin相同user_id的用户,当然，也可以在adminMapper中新增addJoke方
法(我懒得写了，共用的一个-_-!!);<br/>
4.注意mysql8的配置和mysql5.x的不同

#### 部分预览图
#### Android端
<img width="270" height="480" src="https://github.com/xandone/wcdog/blob/master/pic/1.png"></img>
#### 前端
![image](https://github.com/xandone/wcdog/blob/master/pic/2.jpg)
#### 管理系统
![image](https://github.com/xandone/wcdog/blob/master/pic/3.jpg)

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
banned tinyint(1)DEFAULT 0,
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
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## 公告栏表
create table y_plank(
id int(11) unsigned not null auto_increment,
plank_id varchar(18) not null,
content varchar(300) DEFAULT NULL,
send_time datetime,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

## apk版本
create table y_apk(
id int(11) unsigned not null auto_increment,
apk_id varchar(18) not null,
apk_version varchar(20)not null,
apk_url varchar(255)not null,
apk_code int(5)not null,
content varchar(300) not null,
send_time datetime,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

```

## License
MIT