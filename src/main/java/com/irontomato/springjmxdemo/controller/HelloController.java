package com.irontomato.springjmxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @Autowired
    private TestService service;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello "+ name;
    }

    @RequestMapping("/time")
    public String time() throws InterruptedException {
        service.delay(300);
        service.delay(20);
        service.delay(129);
        service.delay(200);
        return new Date().toString();
    }

    public long delay(long d) throws InterruptedException {
        Thread.sleep(d);
        return d;
    }
}
