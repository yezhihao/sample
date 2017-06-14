package org.sample.seckill.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.seckill.model.SeckillRecord;

@Mapper
public interface SeckillRecordMapper {

    int deleteById(@Param("seckillId") Integer seckillId, @Param("userMobile") String userMobile);

    int insert(SeckillRecord record);

    SeckillRecord selectById(@Param("seckillId") Integer seckillId, @Param("userMobile") String userMobile);

    int update(SeckillRecord record);

    void killProcedure(Map<String, Object> params);

}
