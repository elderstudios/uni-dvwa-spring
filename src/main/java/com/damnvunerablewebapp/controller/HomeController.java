package com.damnvunerablewebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@RequestMapping("")
@Controller
public class HomeController {

    @RequestMapping({"/", ""})
    public String home(){
        return "home";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("user")
    public String user(){
        return "home_user";
    }

    @RequestMapping("admin")
    public String admin(){
        return "home_admin";
    }
}
