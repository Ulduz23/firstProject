package com.abbtech.service.security;

import com.abbtech.model.AppUser;
import com.abbtech.model.Permission;
import com.abbtech.model.Role;
import com.abbtech.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .disabled(Boolean.FALSE.equals(user.getActive()))
                .authorities(extractAuthorities(user))
                .build();
    }

    private Set<GrantedAuthority> extractAuthorities(AppUser user) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();

        if (user.getRoles() == null) {
            return authorities;
        }

        for (Role role : user.getRoles()) {
            if (role.getName() != null && !role.getName().isBlank()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
            if (role.getPermissions() == null) {
                continue;
            }
            for (Permission permission : role.getPermissions()) {
                if (permission.getPermissionCode() != null && !permission.getPermissionCode().isBlank()) {
                    authorities.add(new SimpleGrantedAuthority(permission.getPermissionCode()));
                }
            }
        }

        return authorities;
    }
}
