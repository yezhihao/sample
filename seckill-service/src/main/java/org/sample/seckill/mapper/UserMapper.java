package org.sample.seckill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.sample.seckill.model.entity.User;

@Mapper
public interface UserMapper {

    List<User> select(User user);

    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    User selectByUsername(String username);

    int update(User record);

}