package org.sample.seckill.controller;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.seckill.commons.redis.RedisClient;
import org.sample.exception.APIException;
import org.sample.model.APIResult;
import org.sample.seckill.enums.ResultCodes;
import org.sample.seckill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "test")
@Controller
@RequestMapping(method = RequestMethod.GET)
public class SimpleController {

    @Autowired
    private RedisClient redisUtil;

    @ApiOperation(value = "test")
    @RequestMapping(value = "test")
    @ResponseBody
    public APIResult<User> test(@RequestParam(required = false, defaultValue = "not named") String username) {
        User user = new User();
        user.setUsername(username);
        user.setCreateTime(new Date());
        return new APIResult(user);
    }

    @ApiOperation(value = "testJson")
    @RequestMapping(value = "testJson")
    @ResponseBody
    public APIResult<User> testMap(@RequestBody User param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setCreateTime(new Date());
        return new APIResult(user);
    }

    @ApiOperation(value = "warn")
    @RequestMapping(value = "warn")
    @ResponseBody
    public void testWarn(@RequestParam(required = false, defaultValue = "发生错误！") String message) {
        throw new APIException(ResultCodes.S300, message);
    }

    @ApiOperation(value = "throw")
    @RequestMapping(value = "throw")
    @ResponseBody
    public void testThrow(@RequestParam(required = false, defaultValue = "发生错误！") String message) throws Exception {
        throw new Exception(message);
    }

    @ApiOperation(value = "redis/put")
    @RequestMapping(value = "redis/put")
    @ResponseBody
    public String put(@RequestParam String key, @RequestParam String value) {
        redisUtil.put(key, value);
        return "success";
    }

    @ApiOperation(value = "redis/get")
    @RequestMapping(value = "redis/get")
    @ResponseBody
    public String put(@RequestParam String key) {
        return redisUtil.get(key).toString();
    }
}