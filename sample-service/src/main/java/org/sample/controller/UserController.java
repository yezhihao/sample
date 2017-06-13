package org.sample.controller;

import java.util.List;

import org.sample.api.UserService;
import org.sample.model.APIResult;
import org.sample.model.PageInfo;
import org.sample.model.Pagination;
import org.sample.manager.UserManager;
import org.sample.mapper.UserMapper;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController implements UserService {

    @Autowired
    private UserManager userService;

    @Autowired
    private UserMapper dao;

    /**
     * 使用GET方法 通过URL获得数据
     */
    public APIResult<User> simpleCreate(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        userService.register(user);
        return new APIResult(user);
    }

    /**
     * 使用POST方法 接受报文中的JSON
     */
    public APIResult<User> create(@RequestBody User user) {
        userService.register(user);
        return new APIResult(user);
    }

    public APIResult<User> get(@RequestParam Integer id) {
        User user = dao.selectById(id);
        return new APIResult(user);
    }

    public APIResult<Pagination<User>> search(@RequestParam Integer index, @RequestParam(required = false, defaultValue = "1") Integer groupId) {
        PageInfo pageInfo = new PageInfo(index);
        User user = new User();
        user.setGroupId(groupId);
        user.setPageInfo(pageInfo);

        List<User> list = dao.select(user);
        return new APIResult(new Pagination<>(list, pageInfo));
    }

    public APIResult<Pagination<User>> search(@RequestBody User user) {
        List<User> list = dao.select(user);
        return new APIResult(new Pagination<>(list, user.getPageInfo()));
    }
}