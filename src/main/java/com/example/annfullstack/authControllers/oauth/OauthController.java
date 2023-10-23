package com.example.annfullstack.authControllers.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET}, origins = "http://localhost:3000", maxAge = 3600)
public class OauthController {
    @GetMapping("/takeUrl")
    public String takeUrl (){
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=429566012763-pmja7iqvpr3go1fe7c14gtfskio6d4sc.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/v1/auth/oauth&response_type=code&scope=openid%20profile%20email";

    }

    @GetMapping("/oauth")
    public RedirectView googleTokenGet(@RequestParam String code) throws URISyntaxException, IOException, InterruptedException {
        GoogleAccessService googleAccessService = new GoogleAccessService(code);
        System.out.println(googleAccessService.exchangeCodeForAccessToken(code));
        return new RedirectView("http://localhost:3000");
    }

}
