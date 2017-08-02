package com.irontomato.springjmxdemo.controller;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public long delay(long time) throws InterruptedException {
        Thread.sleep(time);
        return time;
    }
}
