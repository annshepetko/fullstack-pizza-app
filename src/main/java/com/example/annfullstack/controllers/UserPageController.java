package com.example.annfullstack.controllers;

import com.example.annfullstack.models.user.UserPageDetails;
import com.example.annfullstack.services.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class UserPageController {
    private final UserPageService userPageService;
    @GetMapping("/profile")
    public ResponseEntity<UserPageDetails> getProfileCredentials(@RequestParam String token) throws URISyntaxException, IOException, InterruptedException {

        return ResponseEntity.ok(userPageService.getUserDetails(token));
    }

}
