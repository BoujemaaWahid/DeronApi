package com.deron.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeconnectBodyDto implements Serializable {
    @JsonProperty("con_id")
    private Long id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("person_id")
    private Long person;
}
