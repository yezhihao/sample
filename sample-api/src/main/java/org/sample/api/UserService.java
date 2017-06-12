package org.sample.api;

import org.sample.model.PaginationResult;
import org.sample.model.User;

public interface UserService {

    /** 使用GET方法 通过URL获得数据 */
    User simpleCreate(String username);

    /** 使用POST方法 接受报文中的JSON */
    User create(User user);

    User get(Integer id);

    PaginationResult<User> search(Integer index, Integer groupId);

    PaginationResult<User> search(User user);

}