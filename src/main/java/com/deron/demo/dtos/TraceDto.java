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
    private Date connection_date;
    @JsonProperty("deconnection_date")
    private Date deconnection_date;
    @JsonProperty("city")
    private String city;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;
    @JsonProperty("postal")
    private String postal;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("asn")
    private String asn;
    @JsonProperty("asn_name")
    private String asn_name;
    @JsonProperty("asn_domain")
    private String asn_domain;
    @JsonProperty("asn_route")
    private String asn_route;
    @JsonProperty("asn_tor")
    private String asn_type;
    @JsonProperty("ip")
    private String connection_ip;
    @JsonProperty("device")
    private String connection_device;
    @JsonProperty("sys_device")
    private String system_device;
    @JsonProperty("privacy_vpn")
    private boolean privacy_vpn;
    @JsonProperty("privacy_proxy")
    private boolean privacy_proxy;
    @JsonProperty("privacy_tor")
    private boolean privacy_tor;
    @JsonProperty("isTrusted")
    private Boolean trust_connection;
}
