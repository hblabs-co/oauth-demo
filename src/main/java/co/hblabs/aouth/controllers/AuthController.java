package co.hblabs.aouth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.hblabs.aouth.models.AuthRequest;
import co.hblabs.aouth.models.AuthResponse;
import co.hblabs.aouth.services.AuthService;

@RestController
@RequestMapping("/auth/token")
public class AuthController {
    
    @PostMapping(value="", produces = "application/json")
    public ResponseEntity<Object> signIn(@RequestBody AuthRequest reqBody) {
        try {
            AuthResponse response = AuthService.getInstance().signIn(reqBody.email, reqBody.password);
            return ResponseEntity.ok(response);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }
}
