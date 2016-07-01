CREATE DATABASE IF NOT EXISTS `test`;
use test;

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
    id int(11) AUTO_INCREMENT,
    username varchar(20),
    group_Id int(11),
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Group`;
CREATE TABLE `Group` (
    id int(11) AUTO_INCREMENT,
    name varchar(20),
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;


INSERT INTO `group` (id, name) VALUES (1, 'Admin');
INSERT INTO `group` (id, name) VALUES (2, 'Noname');

INSERT INTO user (username, group_Id) VALUES ('zhangsan1', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan2', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan3', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan4', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan5', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan6', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan7', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan8', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan9', 1);
INSERT INTO user (username, group_Id) VALUES ('zhangsan0', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi1', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi2', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi3', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi4', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi5', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi6', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi7', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi8', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi9', 1);
INSERT INTO user (username, group_Id) VALUES ('lisi0', 1);
INSERT INTO user (username, group_Id) VALUES ('wangwu1', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu2', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu3', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu4', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu5', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu6', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu7', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu8', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu9', 2);
INSERT INTO user (username, group_Id) VALUES ('wangwu0', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu1', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu2', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu3', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu4', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu5', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu6', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu7', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu8', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu9', 2);
INSERT INTO user (username, group_Id) VALUES ('zhaoliu0', 2);
