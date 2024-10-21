package com.spring.Glassline.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {


    private final String secretkey = "405d1a18b6450bdc391ec1b3410a43d2b9059650a197bbed4a4c7306398aee8cb10d6a6f07ac65187d40f8426c3dd4120379ebc01be65990a9e0b2b28e6a0dde526d4eed59f4c89c60969fb4c2de519ac3cba5a5b7048b68cd83c55790c82637badc7d740a1217781e93af9dbf37a9ffd47e280a4aa9527f3d2d543bc94ab10a3d30c6676126ad258f4691e4c749ad55f1639026f6f7bae00d42cd8ec562d6cec1853597300b98a589ec1a223f85cd495f67f1d7ef212bee6f8b2994687de1e4d88d2fda3bc2851d696d897ca857b5636a40ec714d4b0420ed7e614a2eb4121f5e75d6fc3d5ffbce3026702c533a0eec4ed9158b07366a16bfc9bec348233fac";

    private static final long TOKEN_VALIDITY = 24 * 60 * 60 * 1000; // 24 hours

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) //TODO : change this to actual
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
