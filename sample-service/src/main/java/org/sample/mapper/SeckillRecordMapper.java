package org.sample.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.sample.model.SeckillRecord;

public interface SeckillRecordMapper {

    int deleteById(@Param("seckillId") Integer seckillId, @Param("userMobile") String userMobile);

    int insert(SeckillRecord record);

    SeckillRecord selectById(@Param("seckillId") Integer seckillId, @Param("userMobile") String userMobile);

    int update(SeckillRecord record);

    void killProcedure(Map<String, Object> params);

}
