package com.deron.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto implements Serializable {
    private  static  final  long serialVersionUID =  1L;
    @JsonProperty("error")
    private boolean error = true;
    @JsonProperty("user_id")
    private Long user_id;
    @JsonProperty("con_id")
    private Long con_id;
    @JsonProperty("token")
    private String token;

}
