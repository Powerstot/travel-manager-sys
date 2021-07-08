drop table if exists t_user;
-- 用户表
create table t_user(
   id int(6) primary key auto_increment,
   username varchar(60),
   password varchar(60),
   email varchar(60)
);

drop table if exists t_province;
-- 省份表
create table t_province(
   id int(6) primary key auto_increment,
   name varchar(60),
   tags varchar(80)
);

drop table if exists t_place;
-- 景点表
create table t_place(
    id int(6) primary key auto_increment,
    name varchar(60),
    picpath MEDIUMTEXT,
    hottime TIMESTAMP,
    hotticket double(7,2),
    dimticket double(7,2),
    placedes varchar(300),
    provinceid int(6) REFERENCES t_province(id)
);

drop table if exists t_monitor;
-- 监测照片表
create table t_monitor(
  id int(6) primary key auto_increment,
  picpath varchar(100) comment '图片路径',
  shottime TIMESTAMP comment '拍摄时间',
  ischecked tinyint(1) comment '是否已被检查'
)