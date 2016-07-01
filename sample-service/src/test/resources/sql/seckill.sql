CREATE DATABASE IF NOT EXISTS `test`;
use test;

DROP TABLE IF EXISTS `seckill`;
CREATE TABLE seckill (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    count int NOT NULL,
    start_time datetime NOT NULL,
    end_time datetime NOT NULL,
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX idx_start_time (start_time),
    INDEX idx_end_time (end_time),
    INDEX idx_create_time (create_time)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `seckill_record`;
CREATE TABLE seckill_record (
    seckill_id int(11) NOT NULL,
    user_mobile varchar(16) NOT NULL,
    create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (seckill_id, user_mobile),
    INDEX idx_create_time (create_time)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO test.seckill (name, count, start_time, end_time) VALUES 
('100元秒杀 iPad mini',	100, DATE_ADD(NOW(), interval -2 day), DATE_ADD(NOW(), interval -1 day)),
('200元秒杀 iPad Pro',	100, DATE_ADD(NOW(), interval -1 hour), DATE_ADD(NOW(), interval 1 hour)),
('300元秒杀 iPhone 5',	100, DATE_ADD(NOW(), interval 1 day), DATE_ADD(NOW(), interval 2 day)),
('500元秒杀 iPhone 5s',	100, DATE_ADD(NOW(), interval 3 day), DATE_ADD(NOW(), interval 4 day)),
('700元秒杀 iPhone 6',	100, DATE_ADD(NOW(), interval 5 day), DATE_ADD(NOW(), interval 6 day)),
('900元秒杀 iPhone 6s',	100, DATE_ADD(NOW(), interval 7 day), DATE_ADD(NOW(), interval 8 day));
