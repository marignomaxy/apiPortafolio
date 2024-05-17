package com.apirest.portafolio.authentication;

import com.apirest.portafolio.configuration.JwtService;
import com.apirest.portafolio.entitys.Role;
import com.apirest.portafolio.entitys.User;
import com.apirest.portafolio.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final IUserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .firstname(request.getFirstname())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.ADMIN)
                                .build();

                userRepository.save(user);

                var jwt = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .token(jwt)
                                .build();
        }

        public AuthenticationResponse login(AuthenticationRequest request) {
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow();

                var jwt = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .token(jwt)
                                .build();

        }
}
