package com.abbtech.service.security;

import com.abbtech.dto.security.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final SecurityProperties props;

    public JwtService(SecurityProperties props) {
        this.props = props;
    }


    private SecretKey key() {
        return Keys.hmacShaKeyFor(props.getJwt().getSecret().getBytes(StandardCharsets.UTF_8));
    }

    private String buildToken(UserDetails user, long exp, String type) {

        List<String> auth = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer("abbtech")
                .claim("auth", auth)
                .claim("type", type) // access / refresh
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(key())
                .compact();
    }

    public String accessToken(UserDetails user) {
        return buildToken(user, props.getJwt().getAccessExp(), "access");
    }

    public String refreshToken(UserDetails user) {
        return buildToken(user, props.getJwt().getRefreshExp(), "refresh");
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

