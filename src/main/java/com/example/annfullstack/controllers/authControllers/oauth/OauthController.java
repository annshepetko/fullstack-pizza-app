package com.example.annfullstack.controllers.authControllers.oauth;

import com.example.annfullstack.controllers.authControllers.jwtAuth.responseModels.AuthenticationResponse;
import com.example.annfullstack.controllers.authControllers.oauth.services.GoogleAccessService;
import com.example.annfullstack.controllers.authControllers.oauth.services.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/takeUrl") // повертає редірект адресу на авторизацію через google account для фронтенду
    public String takeUrl (){

        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=429566012763-pmja7iqvpr3go1fe7c14gtfskio6d4sc.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/v1/auth/oauth&response_type=code&scope=openid%20profile%20email";

    }
    @GetMapping("/oauth") // проводить весь процес авторизації черех google сервіс
    public ResponseEntity<Object> googleTokenGet(@RequestParam String code) throws URISyntaxException, IOException, InterruptedException {
        GoogleAccessService googleAccessService = new GoogleAccessService(code);
        String email = googleAccessService.exchangeAccessTokenForUserCredentials().getEmail();
        AuthenticationResponse authenticationResponse = oauthService.authenticate(email);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:3000/authenticate?token=" + authenticationResponse.getToken());

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
