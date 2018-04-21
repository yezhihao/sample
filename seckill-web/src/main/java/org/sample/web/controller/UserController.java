package org.sample.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.sample.model.APIResult;
import org.sample.seckill.model.entity.User;
import org.sample.web.config.CookieName;
import org.sample.web.config.SessionKey;
import org.sample.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"login", "/"}, method = RequestMethod.GET)
    public String welcome(
            @RequestParam(value = "from", required = false, defaultValue = "/index.html") String from,
            @CookieValue(value = "username", required = false) String username,
            @CookieValue(value = "token", required = false) String token,
            HttpServletResponse response,
            HttpSession session,
            Map<String, Object> model) throws IOException {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(token)) {
            APIResult<User> result = userService.loginByToken(username, token, null);

            if (result.isSuccess()) {
                session.setAttribute(SessionKey.USER, result.getData());
                response.sendRedirect(from);
                return null;
            }
        }

        model.put("time", new Date());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "from", required = false, defaultValue = "/index.html") String from,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "remember", required = false, defaultValue = "false") boolean remember,
                        HttpServletResponse response,
                        HttpSession session,
                        Map<String, Object> model) throws IOException {
        APIResult<User> result = userService.login(username, password, "");
        if (result.isSuccess()) {
            User user = result.getData();
            session.setAttribute(SessionKey.USER, user);
            if (remember) {
                response.addCookie(newCookie(CookieName.USERNAME, username, 864000));
                response.addCookie(newCookie(CookieName.TOKEN, user.getPassword(), 864000));
            }
            response.sendRedirect(from);
            return null;
        } else {
            model.put("time", new Date());
            model.put("message", result.getMsg());
            return "login";
        }
    }

    private static Cookie newCookie(String name, String value, int age) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        return cookie;

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, HttpSession session) {
        response.addCookie(newCookie(CookieName.USERNAME, null, 0));
        response.addCookie(newCookie(CookieName.TOKEN, null, 0));
        session.invalidate();
        return "redirect:/user/login";
    }

}