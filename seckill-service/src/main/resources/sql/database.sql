-- CREATE SCHEMA IF NOT EXISTS `test`;
-- use test;
--
-- DROP TABLE IF EXISTS `seckill`;
-- DROP TABLE IF EXISTS `seckill_record`;
-- DROP TABLE IF EXISTS `user`;
-- DROP TABLE IF EXISTS `group`;

CREATE TABLE IF NOT EXISTS `seckill` (
    id int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    name varchar(100) NOT NULL COMMENT '商品名称',
    count int UNSIGNED NOT NULL COMMENT '库存数量',
    start_time datetime NOT NULL COMMENT '开始时间',
    end_time datetime NOT NULL COMMENT '结束时间',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_seckill_start_time (start_time),
    INDEX idx_seckill_end_time (end_time),
    INDEX idx_seckill_create_time (create_time),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `seckill_record` (
    seckill_id int(11) UNSIGNED NOT NULL COMMENT '商品ID',
    user_mobile varchar(16) NOT NULL COMMENT '用户手机',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_seckill_record_create_time (create_time),
    PRIMARY KEY (seckill_id, user_mobile)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
    id int(11) UNSIGNED AUTO_INCREMENT COMMENT '用户ID',
    username varchar(20) COMMENT '用户名',
    password char(32) COMMENT '密码',
    group_id int(11) UNSIGNED COMMENT '用户组ID',
    gender TINYINT(2) UNSIGNED COMMENT '性别',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id),
    UNIQUE KEY idx_username(username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `group` (
    id int(11) UNSIGNED AUTO_INCREMENT COMMENT '用户组ID',
    name varchar(20) COMMENT '组名称',
    update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
