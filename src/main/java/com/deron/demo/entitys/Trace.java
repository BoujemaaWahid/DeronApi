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
    private String country;
    private String hostname;
    private String ip;
    private String loc;
    private String org;
    private String postal;
    private String region;
    private String timezone;
    private Boolean trust_connection;
}
