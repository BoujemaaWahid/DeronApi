package com.deron.demo.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
    public JwtUser validate(String token){
        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JwtHandler.SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }catch(Exception ex){
            System.err.println("TOKEN VALIDATION FAILED: " + ex.getMessage());
        }
        return jwtUser;
    }
}
