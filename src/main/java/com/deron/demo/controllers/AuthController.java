package com.deron.demo.controllers;

import com.deron.demo.dtos.LoginDto;
import com.deron.demo.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/deron/auth")
public class AuthController {
    @Autowired
    private PersonServices services;

    @GetMapping("/ok")
    public String home(){
        return "hello";
    }
    @GetMapping("/hello")
    public LoginDto hello(){

        return services.canLogin("a@a.a", "0000");

        //return services.getPersonByEmail("boujemaa.wahid@gmail.com");
        //return "Hello 45";
    }
    @GetMapping("/ko")
    public String ko(){ return "ko"; }
    
}
