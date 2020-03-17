package com.netease.cloud.skiff.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController extends AbstractController {
    @RequestMapping("/")
    public Object testHarborAPI() {
        return "hello world";
    }
}
