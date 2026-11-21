package co.hblabs.aouth.services;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import co.hblabs.aouth.managers.UsersManager;
import co.hblabs.aouth.models.AuthResponse;
import co.hblabs.aouth.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthService {

    private static AuthService instance;
    private static String MY_SECRET = "c295X3VuX3NlY3JldG9fZnVlcnRlX3NveV91bl9zZWNyZXRvX2Z1ZXJ0ZV9zb3lfdW5fc2VjcmV0b19mdWVydGU="; 

    private AuthService(){}

    public static AuthService getInstance() {

        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }


    public String createToken(User user) {
        Instant now = Instant.now();

        String sub = user.getEmail();
        String jwtToken = Jwts.builder()
            .setSubject(sub)
            .claim("firstName", user.getFirstName())
            .claim("lastName", user.getLastName())
            .claim("email", user.getEmail())
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
            .signWith(getSecretKey())
            .compact();

        return jwtToken;

    }   

    public static Key getSecretKey() {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(AuthService.MY_SECRET), 
                            SignatureAlgorithm.HS512.getJcaName());
        return hmacKey;
    }

    public AuthResponse signIn(String email, String password) throws Exception {
        boolean isUserLoggedIn = false;
        String token = "";

        User foundUser = UsersManager.getInstance().findByEmailUNSAFE(email);
        
        if (!password.equals("") && password.equals(foundUser.getPassword())) {
            isUserLoggedIn = true;
            token = this.createToken(foundUser);
            return new AuthResponse(token, isUserLoggedIn);
        } else {
            throw new Exception("forbidden");
        }
        
    }

    public Jws<Claims> verifyToken(String jwtString) {
        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jwtString);

        return jwt;
    }
}
