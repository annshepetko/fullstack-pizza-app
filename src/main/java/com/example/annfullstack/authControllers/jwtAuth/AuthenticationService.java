package com.example.annfullstack.authControllers.jwtAuth;

import com.example.annfullstack.authControllers.jwtAuth.requestModels.AuthenticationRequest;
import com.example.annfullstack.authControllers.jwtAuth.requestModels.RegisterRequest;
import com.example.annfullstack.authControllers.jwtAuth.responseModels.AuthenticationResponse;
import com.example.annfullstack.config.JwtService;
import com.example.annfullstack.models.user.Role;
import com.example.annfullstack.models.user.User;
import com.example.annfullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(RegisterRequest registerRequest ) throws Exception {

        var user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .created(new Date())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        UserDetails user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public boolean isTokenValid(String jwt){
        String email = jwtService.extractUserEmail(jwt);
        User user = userRepository.findByEmail(email).orElseThrow();

        if(jwtService.isTokenValid(jwt, user)) return true;

        return false;
    }
}
