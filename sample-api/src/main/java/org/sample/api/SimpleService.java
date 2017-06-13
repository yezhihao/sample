package org.sample.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sample.model.APIResult;
import org.sample.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "test")
@RequestMapping(method = RequestMethod.GET)
public interface SimpleService {

    @ApiOperation(value = "test")
    @RequestMapping(value = "test")
    @ResponseBody
    APIResult<User> test(String name);

    @ApiOperation(value = "testJson")
    @RequestMapping(value = "testJson")
    @ResponseBody
    APIResult<User> testMap(@RequestBody User param);

    @ApiOperation(value = "warn")
    @RequestMapping(value = "warn")
    @ResponseBody
    void testWarn(String message);

    @ApiOperation(value = "throw")
    @RequestMapping(value = "throw")
    @ResponseBody
    void testThrow(String message) throws Exception;

    @ApiOperation(value = "redis/put")
    @RequestMapping(value = "redis/put")
    @ResponseBody
    String put(String key, String value);

    @ApiOperation(value = "redis/get")
    @RequestMapping(value = "redis/get")
    @ResponseBody
    String put(String key);

}