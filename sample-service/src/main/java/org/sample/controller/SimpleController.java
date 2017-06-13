package org.sample.controller;

import java.util.Date;

import org.sample.api.SimpleService;
import org.sample.commons.redis.RedisClient;
import org.sample.enums.ResultCode;
import org.sample.exception.APIException;
import org.sample.model.APIResult;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController implements SimpleService {

    @Autowired
    private RedisClient redisUtil;

    public APIResult<User> test(@RequestParam(required = false, defaultValue = "not named") String username) {
        User user = new User();
        user.setUsername(username);
        user.setCreateTime(new Date());
        return new APIResult(user);
    }

    public APIResult<User> testMap(@RequestBody User param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setCreateTime(new Date());
        return new APIResult(user);
    }

    public void testWarn(@RequestParam(required = false, defaultValue = "发生错误！") String message) {
        throw new APIException(ResultCode.S300, message);
    }

    public void testThrow(@RequestParam(required = false, defaultValue = "发生错误！") String message) throws Exception {
        throw new Exception(message);
    }

    public String put(@RequestParam String key, @RequestParam String value) {
        redisUtil.put(key, value);
        return "success";
    }

    public String put(@RequestParam String key) {
        return redisUtil.get(key).toString();
    }
}