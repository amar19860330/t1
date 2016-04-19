package com.t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author amar
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "test/t1";
    }

	@RequestMapping(value = "/login")
    public String login(){
        return "test/t2";
    }
}
