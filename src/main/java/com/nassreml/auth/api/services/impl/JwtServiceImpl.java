package com.nassreml.auth.api.services.impl;

import com.nassreml.auth.api.common.dtos.TokenResponse;
import com.nassreml.auth.api.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
        this.secretToken = secretToken;
    }


    @Override
    public TokenResponse generateToken(Long userId) {
        LocalDateTime expirationDate = LocalDate.now().plusDays(7).atStartOfDay();
        String token = Jwts.builder()
                .subject(userId.toString())
                .issuedAt(
                        Date.from(
                                LocalDate.now()
                                        .atStartOfDay()
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant())
                )
                .expiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(getSecretKey(), Jwts.SIG.HS512)
                .compact();
        return TokenResponse.builder().accessToken(token).build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // TODO: es true o false?
        }
    }

    @Override
    public Integer extractUserId(String token) {
        try {
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    private SecretKey getSecretKey() {
        byte[] secretBytes = Decoders.BASE64.decode(secretToken);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
