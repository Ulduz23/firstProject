package com.abbtech.iamservice.login.service;

import com.abbtech.iamservice.login.dto.RegisterRequestDto;
import com.abbtech.iamservice.common.exception.AuthErrorEnum;
import com.abbtech.iamservice.common.exception.AuthException;
import com.abbtech.iamservice.appuser.model.AppUser;
import com.abbtech.iamservice.role.model.Role;
import com.abbtech.iamservice.role.repository.RoleRepository;
import com.abbtech.iamservice.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(RegisterRequestDto req) {

        if (userRepository.findByUsername(req.username()).isPresent()) {
            throw new AuthException(AuthErrorEnum.USERNAME_EXISTS);
        }

        Role userRole = roleRepo.findByName("USER").orElseThrow();

        AppUser user = new AppUser();
        user.setUsername(req.username());
        user.setFullName(req.fullName());
        user.setEmail(req.email());

        user.setPasswordHash(encoder.encode(req.password()));
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
    }
}

