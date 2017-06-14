package org.sample.web.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    private String message = "Hello World";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "index";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(@RequestParam int page, Map<String, Object> model) {
        model.put("totalPage", 25);
        model.put("page", page);
        return "test";
    }
}