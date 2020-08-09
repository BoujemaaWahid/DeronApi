package com.deron.demo.security.jwt;

public class JwtUser {
    private String username, role, password;
    private Long id;
    public void setUsername(String username){ this.username = username; }
    public void setId(Long id) { this.id = id; }
    public void setRole(String roles){ this.role = roles; }
    public void setPassword(String password) { this.password = (password == null||password.isEmpty())?"":password; }
    public String getPassword() { return password; }
    public Long getId() { return id; }
    public String getRole() { return role; }
    public String getUsername() { return username; }
}

