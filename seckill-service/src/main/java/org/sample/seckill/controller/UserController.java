package org.sample.seckill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.sample.component.mybatis.Page;
import org.sample.model.APIResult;
import org.sample.model.PageInfo;
import org.sample.model.Pagination;
import org.sample.seckill.mapper.UserMapper;
import org.sample.seckill.model.entity.User;
import org.sample.seckill.model.enums.ResultCodes;
import org.sample.seckill.service.UserService;
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
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            return new APIResult(ResultCodes.S300);
        userService.register(user);
        return new APIResult(user);
    }

    @ApiOperation(value = "login")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<User> login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam(value = "slat", required = false) String slat) {
        User user = userService.login(username, password, slat);
        if (user != null)
            return new APIResult(user);
        return new APIResult(ResultCodes.NotFoundUser);
    }

    @ApiOperation(value = "loginByToken")
    @RequestMapping(value = "loginByToken", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<User> loginByToken(@RequestParam("username") String username,
                                        @RequestParam("token") String token,
                                        @RequestParam(value = "slat", required = false) String slat) {
        User user = userService.loginByToken(username, token, slat);
        if (user != null)
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
        Pagination result = Page.start(() -> dao.select(user), pageInfo);

        return new APIResult(result);
    }

    @ApiOperation(value = "search")
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Pagination<User>> search(@RequestBody User user, PageInfo pageInfo) {
        Pagination result = Page.start(() -> dao.select(user), pageInfo);
        return new APIResult(result);
    }
}