package org.fintech.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ClockProvider;
import lombok.RequiredArgsConstructor;
import org.fintech.config.JwtProperty;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperty jwtProperty;

    private final ClockProvider clockProvider;


    private Date jwtClock() {
        return Date.from(clockProvider.getClock().instant());
    }

    public String createToken(long userId, Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(userId))
                .issuedAt(jwtClock())
                .expiration(new Date(jwtClock().getTime() + jwtProperty.getExpirationTime().toMillis()))
                .signWith(getSigningKey())
                .compact();
    }

    public OffsetDateTime getExpiration(String token) {
        validateToken(token);
        return extractAllClaims(token).getExpiration().toInstant().atOffset(ZoneOffset.UTC);
    }

    public long getUserId(String token) {
        validateToken(token);
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    public List<String> extractAuthorities(String token) {
        return (List<String>) extractClaim(token, claims -> claims.get("authorities", List.class));
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperty.getSecret()));
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .clock(this::jwtClock)
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private void validateToken(String token) {
        try {
            if (
                    Jwts.parser()
                            .clock(this::jwtClock)
                            .verifyWith(getSigningKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .getExpiration()
                            .before(jwtClock())
            ) {
                throw new ServiceException(ErrorCode.INVALID_TOKEN, "The token expired!");
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INVALID_TOKEN, e.getMessage());
        }
    }
}