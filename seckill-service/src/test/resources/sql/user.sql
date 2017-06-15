CREATE DATABASE IF NOT EXISTS `test`;
use test;

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
    id int(11) AUTO_INCREMENT,
    username varchar(20),
    group_id int(11),
    gender TINYINT(2),
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

INSERT INTO user (username, group_id) VALUES ('zhangsan1', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan2', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan3', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan4', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan5', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan6', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan7', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan8', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan9', 1);
INSERT INTO user (username, group_id) VALUES ('zhangsan0', 1);
INSERT INTO user (username, group_id) VALUES ('lisi1', 1);
INSERT INTO user (username, group_id) VALUES ('lisi2', 1);
INSERT INTO user (username, group_id) VALUES ('lisi3', 1);
INSERT INTO user (username, group_id) VALUES ('lisi4', 1);
INSERT INTO user (username, group_id) VALUES ('lisi5', 1);
INSERT INTO user (username, group_id) VALUES ('lisi6', 1);
INSERT INTO user (username, group_id) VALUES ('lisi7', 1);
INSERT INTO user (username, group_id) VALUES ('lisi8', 1);
INSERT INTO user (username, group_id) VALUES ('lisi9', 1);
INSERT INTO user (username, group_id) VALUES ('lisi0', 1);
INSERT INTO user (username, group_id) VALUES ('wangwu1', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu2', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu3', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu4', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu5', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu6', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu7', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu8', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu9', 2);
INSERT INTO user (username, group_id) VALUES ('wangwu0', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu1', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu2', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu3', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu4', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu5', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu6', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu7', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu8', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu9', 2);
INSERT INTO user (username, group_id) VALUES ('zhaoliu0', 2);
