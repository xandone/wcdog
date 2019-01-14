package com.xandone.wcdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/front")
    public String frontHome() {
        return "/view/manager/home";
    }
}
