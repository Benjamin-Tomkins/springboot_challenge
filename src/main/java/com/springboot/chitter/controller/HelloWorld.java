package com.springboot.chitter.controller;


import com.springboot.chitter.bean.HelloWorldJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST Controller
@RestController
public class HelloWorld {

    // GET
    // URI - /hello-world
    // JSON - "Hello World"
    @GetMapping(path = "/hello-world")
    public HelloWorldJson helloWorld() {
        return new HelloWorldJson("Hello World");
    }

}
