package org.sample.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(@RequestParam int page, Map<String, Object> model) {
        model.put("totalPage", 25);
        model.put("page", page);
        return "test";
    }
}