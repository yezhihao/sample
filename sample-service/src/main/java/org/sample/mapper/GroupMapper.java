package org.sample.mapper;

import org.sample.model.Group;

public interface GroupMapper {

    int deleteById(Integer id);

    int insert(Group record);

    Group selectById(Integer id);

    int update(Group record);

}
