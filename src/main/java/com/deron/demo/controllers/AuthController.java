package com.deron.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/deron/auth")
public class AuthController {
    @GetMapping("/ok")
    public String home(){
        return "hello";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello 45";
    }
    @GetMapping("/ko")
    public String ko(){ return "ko"; }
    
}
