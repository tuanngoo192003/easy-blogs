package com.project.core.config.security;

import com.project.core.exception.SystemBadRequestException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

    @Value("${jwt.access-token.expiration}")
    protected Long expirationTime;

    @Value("${jwt.refresh-token.expiration}")
    protected Long expirationRefreshTime;

    @Value("${jwt.secret-key}")
    protected String secretKeyValue;

    private final UserDetailsService accountDetails;
    protected static final String AUTH_PREFIX = "Bearer ";

    public String createToken(String username) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.expirationTime);

        return Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .claim("username", username)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSecretKey(), Jwts.SIG.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = accountDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(AUTH_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new SystemBadRequestException("INVALID_TOKEN");
        }
    }

    public boolean isTimeRefreshTokenExpired(LocalDateTime expirationDate) {
        return expirationDate.isBefore(LocalDateTime.now());
    }

    public LocalDateTime getExpireTime() {
        return LocalDateTime.now().plusMinutes(expirationRefreshTime / 1000 / 60);
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(secretKeyValue.getBytes()).getBytes());
    }
}
