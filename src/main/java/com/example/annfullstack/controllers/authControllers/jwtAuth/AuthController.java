package com.example.annfullstack.controllers.authControllers.jwtAuth;


import com.example.annfullstack.controllers.authControllers.jwtAuth.requestModels.AuthenticationRequest;
import com.example.annfullstack.controllers.authControllers.jwtAuth.requestModels.RegisterRequest;
import com.example.annfullstack.controllers.authControllers.jwtAuth.responseModels.AuthenticationResponse;
import com.example.annfullstack.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(methods = {RequestMethod.POST}, origins = "http://localhost:3000", maxAge = 3600)
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    @PostMapping(value =  "/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) throws Exception {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authRequest
            ){
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @GetMapping("/isTokenValid")
    public boolean isTokenValid(@RequestParam String token){

         return authenticationService.isTokenValid(token);

    }
}
