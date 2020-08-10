package com.deron.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    @JsonProperty("error")
    private boolean error = true;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("token")
    private String token;

}
