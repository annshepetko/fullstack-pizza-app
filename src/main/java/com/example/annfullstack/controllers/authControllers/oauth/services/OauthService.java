package com.example.annfullstack.authControllers.oauth.services;

import com.example.annfullstack.authControllers.jwtAuth.responseModels.AuthenticationResponse;
import com.example.annfullstack.config.JwtService;
import com.example.annfullstack.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OauthService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationResponse authenticate(String email){
        User user =  (User) userDetailsService.loadUserByUsername(email);

        Authentication authentication = new UsernamePasswordAuthenticationToken(

                user,
                null,
                user.getAuthorities()

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
