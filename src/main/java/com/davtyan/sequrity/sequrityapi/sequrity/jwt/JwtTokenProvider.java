package com.davtyan.sequrity.sequrityapi.sequrity.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.davtyan.sequrity.sequrityapi.dto.login.request.RoleRequest;
import com.davtyan.sequrity.sequrityapi.sequrity.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public String createToken(String userName) {
        Claims claims = Jwts.claims().setSubject(userName);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + validityInMilliseconds))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String authorisation = request.getHeader("Authorisation");
        if (authorisation != null && authorisation.startsWith("Bearer_")) {
            return authorisation.substring(7);
        }
        return null;
    }

    public String getUserName(String token) {// ha esia
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("chi karum token sarqi");
        }
    }

    public List<String> getRoleName(List<RoleRequest> roles) {
        return roles.stream().map(RoleRequest::getName).collect(Collectors.toList());
    }

}
