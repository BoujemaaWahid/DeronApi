package com.deron.demo.dtos;

import com.deron.demo.entitys.Trace;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    private  static  final  long serialVersionUID =  1L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("date_birth")
    private String date_birth;
    @JsonProperty("created_date")
    private String created_date;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("password")
    private String password;
    private String secret_key;
    @JsonProperty("gender")
    private char gender;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("trace")
    private List<Trace> traceList;
    @JsonProperty("is_active_account")
    private Boolean active_account;
    @JsonProperty("is_connected")
    private Boolean connected;
    @JsonProperty("is_offline")
    private Boolean offline;
    @JsonProperty("credit")
    private double credit;
    @JsonProperty("under_max")
    private double under_max;


}
