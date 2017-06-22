package org.sample.web.service;

import org.sample.model.APIResult;
import org.sample.seckill.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${seckill-service}", name = "UserService")
public interface UserService {

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    APIResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password);
}