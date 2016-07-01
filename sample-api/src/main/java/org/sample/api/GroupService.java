package org.sample.api;

import org.sample.model.Group;

public interface GroupService {

    /** 使用GET方法 通过URL获得数据 */
    Group simpleCreate(String name);

    /** 使用POST方法 接受报文中的JSON */
    Group create(Group group);

    Group getGroup(Integer id);

}