package com.deron.demo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date date_birth;
    @Temporal(TemporalType.DATE)
    private Date created_date;
    private String phone;
    @Column(nullable = false)
    private String password;
    private String secret_key;
    private char gender;
    @Lob
    private String signature;
    private String first_name;
    private String last_name;
    @Lob
    private String description;
    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Trace> traceList;
    private Boolean active_account = false;
    private Boolean connected = false;
    private Boolean offline = true;
    private double credit = 250;
    private double under_max = 0;
}
