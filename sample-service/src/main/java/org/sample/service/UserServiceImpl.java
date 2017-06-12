package org.sample.service;

import java.util.List;

import org.sample.api.UserService;
import org.sample.model.PageInfo;
import org.sample.model.PaginationResult;
import org.sample.manager.UserManager;
import org.sample.mapper.UserMapper;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userService;

    @Autowired
    private UserMapper dao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    /** 使用GET方法 通过URL获得数据 */
    public User simpleCreate(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        userService.register(user);
        return user;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    /** 使用POST方法 接受报文中的JSON */
    public User create(@RequestBody User user) {
        userService.register(user);
        return user;
    }

    @RequestMapping(value = "get")
    @ResponseBody
    public User get(@RequestParam Integer id) {
        User user = dao.selectById(id);
        return user;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public PaginationResult<User> search(@RequestParam Integer index, @RequestParam(required = false, defaultValue = "1") Integer groupId) {
        PageInfo pageInfo = new PageInfo(index);
        User user = new User();
        user.setGroupId(groupId);
        user.setPageInfo(pageInfo);

        List<User> list = dao.select(user);
        return new PaginationResult<>(list, pageInfo);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public PaginationResult<User> search(@RequestBody User user) {
        List<User> list = dao.select(user);
        return new PaginationResult<>(list, user.getPageInfo());
    }
}