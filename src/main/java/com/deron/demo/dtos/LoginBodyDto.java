package com.deron.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginBodyDto implements Serializable {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("con_date")
    private String connection_date;
    @JsonProperty("con_place")
    private String connection_place;
    @JsonProperty("con_device")
    private String connection_device;
    @JsonProperty("sys_device")
    private String system_device;
}
