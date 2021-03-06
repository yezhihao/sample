package org.sample.seckill.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.LRUMap;
import org.sample.commons.lang.DateUtils;
import org.sample.model.exception.APIException;
import org.sample.seckill.model.vo.Exposer;
import org.sample.seckill.model.enums.ResultCodes;
import org.sample.seckill.service.SeckillService;
import org.sample.seckill.mapper.SeckillMapper;
import org.sample.seckill.mapper.SeckillRecordMapper;
import org.sample.seckill.model.entity.Seckill;
import org.sample.seckill.model.entity.SeckillRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {

    @SuppressWarnings("unchecked")
    private static volatile Map<Integer, Seckill> ExposerCache = new LRUMap(10);

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SeckillRecordMapper recordMapper;

    private static final String slat = "test";

    @Override
    public Exposer exportSeckillUrl(int seckillId) {
// Seckill seckill = seckillMapper.selectById(seckillId);
        Seckill seckill = getSeckill(seckillId);
        if (seckill == null)
            return new Exposer(false, seckillId);
        LocalDateTime startTime = seckill.getStartTime();
        LocalDateTime endTime = seckill.getEndTime();
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime))
            return new Exposer(false, seckillId, DateUtils.getMillis(currentTime), DateUtils.getMillis(startTime), DateUtils.getMillis(endTime));

        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /** 缓存秒杀库存对象 */
    private Seckill getSeckill(int seckillId) {
        Seckill seckill = ExposerCache.get(seckillId);
        if (seckill == null) {
            synchronized (ExposerCache) {
                seckill = ExposerCache.get(seckillId);
                if (seckill == null) {
                    seckill = seckillMapper.selectById(seckillId);
                    ExposerCache.put(seckillId, seckill);
                }
            }
        }
        return seckill;
    }

    private static String getMD5(int seckillId) {
        String a = new StringBuilder().append(seckillId).append("/").append(slat).toString();
        String md5 = DigestUtils.md5DigestAsHex(a.getBytes());
        return md5;
    }

    /** 通过spring声明式事务保证原子性 */
    @Transactional
    @Override
    public SeckillRecord executeSeckill(int seckillId, String userMobile, String md5) {
        if (md5 == null || !md5.equalsIgnoreCase(getMD5(seckillId)))
            throw new APIException(ResultCodes.S401);

        SeckillRecord record = new SeckillRecord(seckillId, userMobile);
        int row = recordMapper.insert(record);
        if (row <= 0) {
            throw new APIException(ResultCodes.S403);
        } else {
            row = seckillMapper.reduceCount(seckillId, new Date());
            if (row <= 0)
                throw new APIException(ResultCodes.S402);
        }
        return record;
    }

    /** 通过存储过程保证原子性 */
    @Override
    public SeckillRecord executeSeckillProcedure(int seckillId, String userMobile, String md5) {
        if (md5 == null || !md5.equalsIgnoreCase(getMD5(seckillId)))
            throw new APIException(ResultCodes.S401);
        LocalDateTime killTime = LocalDateTime.now();
        Map<String, Object> params = new HashMap<>();
        params.put("seckillId", seckillId);
        params.put("userMobile", userMobile);
        params.put("killTime", killTime);
        params.put("result", null);
        recordMapper.killProcedure(params);
        Integer row = MapUtils.getInteger(params, "result", -3);
        if (row == 0)
            return new SeckillRecord(seckillId, userMobile, killTime);
        else if (row == -1)
            throw new APIException(ResultCodes.S403);
        else if (row == -2)
            throw new APIException(ResultCodes.S402);
        else
            throw new APIException(ResultCodes.S500);
    }
}