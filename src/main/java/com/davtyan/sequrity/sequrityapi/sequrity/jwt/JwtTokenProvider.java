package com.davtyan.sequrity.sequrityapi.sequrity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    public String createToken(String userName) {
        Claims claims = Jwts.claims().setSubject(userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
