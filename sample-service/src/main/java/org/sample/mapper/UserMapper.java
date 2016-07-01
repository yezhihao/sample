package org.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.sample.model.User;

@Mapper
public interface UserMapper {

    List<User> select(User user);

    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int update(User record);

}