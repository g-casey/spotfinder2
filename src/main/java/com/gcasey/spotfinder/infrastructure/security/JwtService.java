package com.gcasey.spotfinder.infrastructure.security;

import com.gcasey.spotfinder.data.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    private final String jwtKey;
    private final UserRepository userRepository;

    @Autowired
    public JwtService(@Value("${jwt.key}") String jwtKey,
                      UserRepository userRepository) {
        this.jwtKey = jwtKey;
        this.userRepository = userRepository;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public boolean isTokenValid(String jwt, String email) {
        return getEmail(jwt).equals(email) && !parseToken(jwt).getExpiration().before(new Date());
    }

    public String generateToken(String email) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
    }

    public String getEmail(String jwt) {
        return parseToken(jwt).getSubject();
    }
}
