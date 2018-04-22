package org.sample.seckill.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sample.seckill.model.entity.Group;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupMapper {

    int deleteById(Integer id);

    int insert(Group record);

    Group selectById(Integer id);

    int update(Group record);

}
