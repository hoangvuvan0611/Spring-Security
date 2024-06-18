package org.vvh.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vvh.springsecurity.dto.request.LoginRequest;
import org.vvh.springsecurity.dto.request.SignUpRequest;
import org.vvh.springsecurity.dto.response.LoginResponse;
import org.vvh.springsecurity.entity.Role;
import org.vvh.springsecurity.entity.User;
import org.vvh.springsecurity.enums.RoleEnum;
import org.vvh.springsecurity.enums.TokenTypeEnum;
import org.vvh.springsecurity.exception.AlreadyExistsException;
import org.vvh.springsecurity.exception.ResourceNotFoundException;
import org.vvh.springsecurity.repository.RoleRepository;
import org.vvh.springsecurity.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    @Override
    public void registerUser(SignUpRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.username())){
            throw new AlreadyExistsException(registerRequest.username() + " already exists");
        }
        User user = User.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();
        Set<Role> roles = new HashSet<>();

        if(registerRequest.role() == null) {
            Role role = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            roles.add(role);
        } else {
            registerRequest.role().forEach(r -> {
                Role role = roleRepository.findByName(RoleEnum.valueOf(r))
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
                roles.add(role);
                role.getUsers().add(user);
                roleRepository.save(role);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtProvider.generateToken(user, TokenTypeEnum.ACCESS_TOKEN);
        String refreshToken = jwtProvider.generateToken(user, TokenTypeEnum.REFRESH_TOKEN);
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return LoginResponse.builder()
                .username(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .roles(roles)
                .build();
    }
}
