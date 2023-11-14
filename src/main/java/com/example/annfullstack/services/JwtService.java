package com.example.annfullstack.services;

import com.example.annfullstack.models.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "5fb917ae95f03f6258f00d55b04ad6e86d78968090c07c7c32895c924e2ed4b0";

    private final UserDetailsService userDetailsService;
    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        Claims claims  = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUserEmail(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractClaim(token, Claims::getSubject);
        if (username.equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true;
        }else {
            return false;
        }
    }
    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
   public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
       long expirationTime = 30 * 24 * 60 * 60 * 1000L;
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expirationTime))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
   }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public User exchangeTokenForUser(String token){
        String email = this.extractUserEmail(token);
        User user = (User) userDetailsService.loadUserByUsername(email);

        return user;
    }
}
