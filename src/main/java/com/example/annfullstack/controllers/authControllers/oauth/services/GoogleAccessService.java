package com.example.annfullstack.authControllers.oauth.services;

import com.example.annfullstack.authControllers.oauth.response_models.ExchangeCode;
import com.example.annfullstack.authControllers.oauth.response_models.UserCredentials;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class GoogleAccessService {
    private static final String CLIENT_ID = "429566012763-pmja7iqvpr3go1fe7c14gtfskio6d4sc.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";

    private static final String REDIRECT_URI = "http://localhost:8080/api/v1/auth/oauth";
    private String code;

    private ClientRegistration clientRegistration;
    public GoogleAccessService(@Nullable String code){
        this.code = code;
    }
    public String exchangeCodeForAccessToken() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        ExchangeCode exchangedCode = getAccessToken(this.code);
        ObjectMapper objectMapper = new ObjectMapper();

        HttpRequest getUserCredentials = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/oauth2/v3/userinfo"))
                .setHeader("Authorization", exchangedCode.token_type + exchangedCode.access_token)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .GET()
                .build();

        HttpResponse<String> userCredentialsResponse = client.send(getUserCredentials, HttpResponse.BodyHandlers.ofString());
        UserCredentials userCredentials = objectMapper.readValue(userCredentialsResponse.body(), UserCredentials.class);
        return userCredentials.getEmail();
    }
    private ExchangeCode getAccessToken(String code) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody =
                "code=" + code + "&grant_type=authorization_code"
                        + "&client_secret=" + CLIENT_SECRET
                        + "&client_id=" + CLIENT_ID
                        + "&redirect_uri=" + REDIRECT_URI;
        HttpRequest postExchangeRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/oauth2/v4/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> exchangeResponse = client.send(postExchangeRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeCode exchangedCode = objectMapper.readValue(exchangeResponse.body(), ExchangeCode.class);
        return exchangedCode;
    }

}
