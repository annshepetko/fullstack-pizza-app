package com.example.annfullstack.authControllers.oauth;

import com.example.annfullstack.authControllers.jwtAuth.AuthenticationResponse;
import com.example.annfullstack.authControllers.oauth.services.GoogleAccessService;
import com.example.annfullstack.authControllers.oauth.services.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET}, origins = "http://localhost:3000", maxAge = 3600)
public class OauthController {
    @Autowired
    private final OauthService oauthService;
    @GetMapping("/takeUrl")
    public String takeUrl (){
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=429566012763-pmja7iqvpr3go1fe7c14gtfskio6d4sc.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/v1/auth/oauth&response_type=code&scope=openid%20profile%20email";

    }
    @GetMapping("/oauth")
    public String googleTokenGet(@RequestParam String code) throws URISyntaxException, IOException, InterruptedException {
        GoogleAccessService googleAccessService = new GoogleAccessService(code);
        String email = googleAccessService.exchangeCodeForAccessToken();
        System.out.println(oauthService.authenticate(email).getToken());
        System.out.println(email);
        return oauthService.authenticate(email).getToken();
    }

}
