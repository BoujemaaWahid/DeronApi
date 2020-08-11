package com.deron.demo.dtos;

import com.deron.demo.entitys.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("loc")
    private String loc;
    @JsonProperty("org")
    private String org;
    @JsonProperty("postal")
    private String postal;
    @JsonProperty("region")
    private String region;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("isTrusted")
    private Boolean trust_connection;
}
