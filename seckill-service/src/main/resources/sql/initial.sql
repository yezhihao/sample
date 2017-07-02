DELETE FROM `seckill` WHERE id < 7;
INSERT INTO `seckill` (id, name, count, start_time, end_time) VALUES
(1, '100元秒杀 iPad mini', 100, '2000-01-01 00:00:00', '2099-12-31 23:59:59'),
(2, '200元秒杀 iPad Pro', 100, '2099-01-01 00:00:00', '2099-12-31 23:59:59'),
(3, '300元秒杀 iPhone 5', 100, CURRENT_TIMESTAMP, '2099-12-31 23:59:59'),
(5, '700元秒杀 iPhone 6', 100, '2000-01-01 00:00:00', '2000-12-31 23:59:59'),
(4, '500元秒杀 iPhone 5s', 100, CURRENT_TIMESTAMP, '2099-12-31 23:59:59'),
(6, '900元秒杀 iPhone 6s', 100, '2000-01-01 00:00:00', '2099-12-31 23:59:59');

DELETE FROM `group` WHERE id < 3;
INSERT INTO `group`
(id, name) VALUES
(1, 'Admin'),
(2, 'Noname');

DELETE FROM `user` WHERE id < 10;
INSERT INTO `user`
(id, username, group_id, gender, password) VALUES
(1, 'admin', 1, 1, 'e10adc3949ba59abbe56e057f20f883e'),
(2, 'zhangsan1', 1, 1, 'e10adc3949ba59abbe56e057f20f883e'),
(3, 'zhangsan2', 2, 1, 'e10adc3949ba59abbe56e057f20f883e'),
(4, 'lisi1', 1, 1, 'e10adc3949ba59abbe56e057f20f883e'),
(5, 'lisi2', 1, 2, 'e10adc3949ba59abbe56e057f20f883e'),
(6, 'wangwu1', 1, 0, 'e10adc3949ba59abbe56e057f20f883e'),
(7, 'wangwu2', 1, 2, 'e10adc3949ba59abbe56e057f20f883e'),
(8, 'zhaoliu1', 1, 1, 'e10adc3949ba59abbe56e057f20f883e'),
(9, 'zhaoliu2', 1, 0, '123456');