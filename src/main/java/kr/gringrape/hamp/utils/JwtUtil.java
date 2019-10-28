package kr.gringrape.hamp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtUtil {

    private Key secretKey;

    public JwtUtil(String secret) {

        secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    }

    public String createToken(Long userId, String name) {

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("userName", name)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Claims getClaims(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims;

    }
}
