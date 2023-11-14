package com.example.annfullstack.authControllers.oauth.response_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeCode {
    public String access_token;

    public String token_type;
    private String scope;
    public String id_token;
    private int expires_in;
}
