package com.learnsimon.rest.webservices.restful_web_service.helloworld;

import org.springframework.web.bind.annotation.*;

//REST API
@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String hellowWorld(){
        return "Hello worldd";
    }

    @GetMapping(path = "/hello-world-bean")
    public HellowWorldBean hellowWorldBean(){
        return new HellowWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HellowWorldBean hellowWorldBeanPath(@PathVariable String name){
        return new HellowWorldBean(String.format("Hello World, %s", name));
    }



}
