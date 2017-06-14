package org.sample.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sample.seckill.model.Group;

@Mapper
public interface GroupMapper {

    int deleteById(Integer id);

    int insert(Group record);

    Group selectById(Integer id);

    int update(Group record);

}
