package com.deron.demo.dtos;

import com.deron.demo.entitys.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceDto implements Serializable {
    private  static  final  long serialVersionUID =  1L;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("person")
    private Person person;
    @JsonProperty("connection_date")
    private String connection_date;
    @JsonProperty("deconnection_date")
    private String deconnection_date;
    @JsonProperty("connection_place")
    private String connection_place;
    @JsonProperty("connection_device")
    private String connection_device;
    @JsonProperty("system_device")
    private String system_device;
    @JsonProperty("is_trust_connection")
    private Boolean trust_connection;
}
