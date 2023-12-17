package com.ahmed.spring_reddit_clone.configuration.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j // private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class) (the same)
public class JwtUtils {


    @Value("${spring_reddit_clone.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring_reddit_clone.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public String generateJwtTokenByUsername(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(key())
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String  getUsernameFromJwt(String token) {
        String username = Jwts.parser().verifyWith(key())
                .build().parseSignedClaims(token).getPayload().getSubject();
        log.info("username: {}", username);
        return username;
    }

    public Long getJwtExpirationMs() {
        return (long) jwtExpirationMs;
    }

    public Boolean validateJwtToken(String authToken) {
        try {
//            Jwts.parser().decryptWith(key()).build().parse(authToken);
            Jwts.parser().verifyWith(key()).build().parse(authToken);
            return true;

        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
