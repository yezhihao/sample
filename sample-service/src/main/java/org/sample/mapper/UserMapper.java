package org.sample.mapper;

import java.util.List;

import org.sample.model.User;

public interface UserMapper {

    List<User> select(User user);

    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int update(User record);

}