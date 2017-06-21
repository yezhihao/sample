package org.sample.web.controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.sample.commons.lang.RandomUtils;
import org.sample.commons.lang.StringUtils;
import org.sample.model.APIResult;
import org.sample.seckill.model.User;
import org.sample.web.config.SessionKey;
import org.sample.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "Welcome!");
        return "index";
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<User> login(@RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "password", required = false) String password,
                                 HttpSession session) {
        if (StringUtils.isNotBlank(password)) {
            APIResult<User> result = userService.login(username, password);
            if (result.isSuccess()) {
                session.setAttribute(SessionKey.USER, result.getData());
            }
            return result;
        } else {
            username = StringUtils.isBlank(username) ? RandomUtils.nextString(5) : username;
            User user = new User(ThreadLocalRandom.current().nextInt(), username);
            session.setAttribute(SessionKey.USER, user);
            return new APIResult(user);
        }
    }

    @RequestMapping(value = "user/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(@RequestParam int page, Map<String, Object> model) {
        model.put("totalPage", 25);
        model.put("page", page);
        return "test";
    }
}