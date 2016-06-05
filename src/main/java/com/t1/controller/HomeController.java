package com.t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author amar
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String list(){
        //return "home/b_tologin";
        return "forward:user/toLogin";
    }

    @RequestMapping("/login")
    public String login(){
        return "home/b_main";
    }

}
