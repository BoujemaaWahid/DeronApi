package com.deron.demo.entitys;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonIgnore
    private Person person;
    private Date connection_date;
    private Date deconnection_date;
    private String city;
    private String hostname;
    private String region;
    private String country;
    private String postal;
    private String timezone;
    private String asn;
    private String asn_name;
    private String asn_domain;
    private String asn_route;
    private String asn_type;
    private String connection_ip;
    private String connection_device;
    private String system_device;
    private boolean privacy_vpn;
    private boolean privacy_proxy;
    private boolean privacy_tor;
    private Boolean trust_connection;
}
