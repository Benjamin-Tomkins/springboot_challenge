package com.springboot.chitter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // GET
    // URI - /
    // method - shows the index.html template

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}
