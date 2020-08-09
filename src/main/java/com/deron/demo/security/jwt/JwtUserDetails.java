package com.deron.demo.security.jwt;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class JwtUserDetails extends User {
    private String token;
    private Long id;

    public JwtUserDetails(Long id, String username, String password, String token, Collection<? extends GrantedAuthority> authorities) {
        super(username, (password == null || password.isEmpty())?"":password, authorities);
        this.id = id;
        this.token = token;
    }
    public void setToken(String token) { this.token = token; }
    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    public String getToken() { return token; }
}

