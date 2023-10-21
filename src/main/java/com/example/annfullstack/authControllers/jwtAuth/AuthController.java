package com.example.annfullstack.authControllers.jwtAuth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(methods = {RequestMethod.POST}, origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping(value =  "/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authRequest
            ){
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

}
