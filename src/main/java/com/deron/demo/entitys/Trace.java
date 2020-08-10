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
    private String connection_place;
    private String connection_device;
    private String system_device;
    private Boolean trust_connection = true;
}
