package org.sample.service;

import java.util.Date;

import org.sample.api.SimpleService;
import org.sample.commons.redis.RedisClient;
import org.sample.enums.ResultCode;
import org.sample.exception.BusinessException;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    private RedisClient redisUtil;

    @RequestMapping(value = "test")
    @ResponseBody
    public User test(@RequestParam(required = false, defaultValue = "not named") String username) {
        User user = new User();
        user.setUsername(username);
        user.setCreateTime(new Date());
        return user;
    }

    @RequestMapping(value = "testJson")
    @ResponseBody
    public User testMap(@RequestBody User param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setCreateTime(new Date());
        return user;
    }

    @RequestMapping(value = "warn")
    @ResponseBody
    public void testWarn(@RequestParam(required = false, defaultValue = "发生错误！") String message) {
        throw new BusinessException(ResultCode.S300, message);
    }

    @RequestMapping(value = "throw")
    @ResponseBody
    public void testThrow(@RequestParam(required = false, defaultValue = "发生错误！") String message) throws Exception {
        throw new Exception(message);
    }

    @RequestMapping(value = "redis/put")
    @ResponseBody
    public String put(@RequestParam String key, @RequestParam String value) {
        redisUtil.put(key, value);
        return "success";
    }

    @RequestMapping(value = "redis/get")
    @ResponseBody
    public String put(@RequestParam String key) {
        return redisUtil.get(key).toString();
    }
}