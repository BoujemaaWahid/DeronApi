package com.deron.demo.controllers;

import com.deron.demo.dtos.DeconnectBodyDto;
import com.deron.demo.dtos.LoginBodyDto;
import com.deron.demo.dtos.LoginResponseDto;
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
    
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginBodyDto lbd){
        return services.canLogin(lbd);
    }

    @PostMapping("/disconnect")
    public void disconnect(@RequestBody DeconnectBodyDto dbd){
        services.deconnect(dbd);
    }
    @GetMapping("/ko")
    public String ko(){ return "ko"; }
    
}
