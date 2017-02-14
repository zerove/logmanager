/* 创建数据库 */ 
create database db_log4j;

/* 切换数据库 */
use  db_log4j;

/* 日志信息表 */
create table tb_log(
    logId int not null auto_increment comment '流水号' ,
    createDate varchar(45) default null comment '日志生成时间' ,
    thread varchar(45) default null comment '当前线程',
    level varchar(45) default null comment '当前日志级别' ,
    class varchar(45) default null comment '生成日志的类',
    message varchar(245) default null comment '日志具体信息',
    primary key(logId)
);