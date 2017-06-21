package org.sample.seckill.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.model.APIResult;
import org.sample.model.PageInfo;
import org.sample.model.Pagination;
import org.sample.seckill.enums.ResultCodes;
import org.sample.seckill.service.UserService;
import org.sample.seckill.mapper.UserMapper;
import org.sample.seckill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "user")
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper dao;

    /**
     * 使用GET方法 通过URL获得数据
     */
    @ApiOperation(value = "create")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<User> simpleCreate(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        userService.register(user);
        return new APIResult(user);
    }

    /**
     * 使用POST方法 接受报文中的JSON
     */
    @ApiOperation(value = "create")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<User> create(@RequestBody User user) {
        userService.register(user);
        return new APIResult(user);
    }

    @ApiOperation(value = "login")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = dao.selectByUsername(username);
        if (user != null && user.getPassword().equalsIgnoreCase(password))
            return new APIResult(user);
        return new APIResult(ResultCodes.NotFoundUser);
    }

    @ApiOperation(value = "search")
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public APIResult<Pagination<User>> search(@RequestParam Integer index, @RequestParam(required = false, defaultValue = "1") Integer groupId) {
        PageInfo pageInfo = new PageInfo(index);
        User user = new User();
        user.setGroupId(groupId);
        user.setPageInfo(pageInfo);

        List<User> list = dao.select(user);
        return new APIResult(new Pagination(pageInfo, list));
    }

    @ApiOperation(value = "search")
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Pagination<User>> search(@RequestBody User user) {
        List<User> list = dao.select(user);
        return new APIResult(new Pagination(user.getPageInfo(), list));
    }
}