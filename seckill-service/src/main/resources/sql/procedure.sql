DROP PROCEDURE `test`.`execute_seckill`;
SET @r_result = -3;
call execute_seckill(1, '182171231123', now(), @r_result);
select @r_result;

DELIMITER $$
CREATE PROCEDURE `test`.`execute_seckill`
(IN i_seckill_id BIGINT, IN i_user_mobile VARCHAR(16), IN i_kill_time DATETIME, OUT r_result INT)
    BEGIN
        DECLARE insert_count INT DEFAULT 0;
        START TRANSACTION;
        INSERT ignore INTO seckill_record(seckill_record.seckill_id, seckill_record.user_mobile, seckill_record.create_time)
        VALUES(i_seckill_id, i_user_mobile, i_kill_time);
        SELECT row_count() INTO insert_count;
        IF (insert_count = 0) THEN
            ROLLBACK;
            SET r_result = -1;
        ELSEIF (insert_count < 0) THEN
            ROLLBACK;
            SET r_result = -3;
        ELSE
            UPDATE seckill SET count = count - 1 
            WHERE count > 0 AND id = i_seckill_id AND start_time <= i_kill_time AND end_time >= i_kill_time;
            SELECT row_count() INTO insert_count;
            IF (insert_count = 0) THEN
                ROLLBACK;
                SET r_result = -2;
            ELSEIF (insert_count < 0) THEN
                ROLLBACK;
                SET r_result = -4;
            ELSE
                COMMIT;
                SET r_result = 0;
            END IF;
        END IF;
    END;
$$