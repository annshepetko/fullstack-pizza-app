package com.example.annfullstack.authControllers.oauth;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Service

public class GoogleAccessService {
    private static final String CLIENT_ID = "429566012763-pmja7iqvpr3go1fe7c14gtfskio6d4sc.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";

    private static final String REDIRECT_URI = "http://localhost:8080/api/v1/auth/oauth";
    private String code;
    GoogleAccessService(@Nullable String code){
        this.code = code;
    }
    public String exchangeCodeForAccessToken(String code) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody =
                "code=" + this.code + "&grant_type=authorization_code"
                + "&client_secret=" + CLIENT_SECRET
                + "&client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI;
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://www.googleapis.com/oauth2/v4/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

}
