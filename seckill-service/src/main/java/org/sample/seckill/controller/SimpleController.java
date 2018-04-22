package org.sample.seckill.controller;

import java.time.LocalDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.model.APIResult;
import org.sample.model.exception.APIException;
import org.sample.seckill.model.enums.ResultCodes;
import org.sample.seckill.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "test")
@Controller
@RequestMapping(method = RequestMethod.GET)
public class SimpleController {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation(value = "kafka/send")
    @RequestMapping(value = "kafka/send")
    @ResponseBody
    public String send(@RequestParam String topic, @RequestParam String message) {
        kafkaTemplate.send("test1", "test111");
        return "success";
    }

    @ApiOperation(value = "redis/put")
    @RequestMapping(value = "redis/put")
    @ResponseBody
    public String put(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @ApiOperation(value = "redis/get")
    @RequestMapping(value = "redis/get")
    @ResponseBody
    public String put(@RequestParam String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

    @ApiOperation(value = "test")
    @RequestMapping(value = "test")
    @ResponseBody
    public APIResult<User> test(@RequestParam(required = false, defaultValue = "not named") String username) {
        User user = new User();
        user.setUsername(username);
        user.setCreateTime(LocalDateTime.now());
        return new APIResult(user);
    }

    @ApiOperation(value = "testJson")
    @RequestMapping(value = "testJson")
    @ResponseBody
    public APIResult<User> testMap(@RequestBody User param) {
        User user = new User();
        user.setUsername(param.getUsername());
        user.setCreateTime(LocalDateTime.now());
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
}