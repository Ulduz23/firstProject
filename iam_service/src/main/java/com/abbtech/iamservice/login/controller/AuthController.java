package com.abbtech.iamservice.login.controller;

import com.abbtech.iamservice.login.dto.LoginRequestDto;
import com.abbtech.iamservice.login.dto.RegisterRequestDto;
import com.abbtech.iamservice.login.dto.TokenResponseDto;
import com.abbtech.iamservice.common.exception.AuthErrorEnum;
import com.abbtech.iamservice.common.exception.AuthException;
import com.abbtech.iamservice.login.service.JwtService;
import com.abbtech.iamservice.login.service.RegisterService;
import com.abbtech.iamservice.login.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtService jwt;
    private final RegisterService registerService;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto req) {
        Authentication auth =
                manager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        return new TokenResponseDto(jwt.accessToken(user), jwt.refreshToken(user));
    }


    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto req) {
        registerService.register(req);
    }

    @PostMapping("/refresh")
    public TokenResponseDto refresh(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthException(AuthErrorEnum.INVALID_TOKEN);
        }
        Claims claims = jwt.parse(authorizationHeader.substring(7));
        if (!"refresh".equals(claims.get("type"))) {
            throw new AuthException(AuthErrorEnum.INVALID_TOKEN);
        }
        String username = claims.getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return new TokenResponseDto(jwt.accessToken(user), jwt.refreshToken(user));
    }
}

