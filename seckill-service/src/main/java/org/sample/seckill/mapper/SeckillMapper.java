package org.sample.seckill.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.seckill.model.entity.Seckill;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeckillMapper {

    int deleteById(Integer id);

    int insert(Seckill record);

    Seckill selectById(Integer id);

    List<Seckill> select(Seckill record);

    int update(Seckill record);

    /** 减库存 */
    int reduceCount(@Param("id") int id, @Param("killTime") Date killTime);

}