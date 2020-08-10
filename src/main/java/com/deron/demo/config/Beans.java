package com.deron.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Beans {

    @Bean(name="modelmapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name="objectmapper")
    public ObjectMapper objectlMapper() {
        return new ObjectMapper();
    }

    @Bean(name = "bcrypt")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
