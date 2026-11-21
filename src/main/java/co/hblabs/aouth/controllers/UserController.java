package co.hblabs.aouth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.hblabs.aouth.managers.UsersManager;
import co.hblabs.aouth.models.UserRequest;
import co.hblabs.aouth.models.User;
import co.hblabs.aouth.services.AuthService;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value="{email}", produces = "application/json")
    public ResponseEntity<Object> getUser(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable String email
    ) {
        try {
            String tokenWithoutBearer = authorizationHeader.substring("Bearer ".length());
            AuthService.getInstance().verifyToken(tokenWithoutBearer);
            User foundUser = UsersManager.getInstance().findByEmailSAFE(email);
            
            return ResponseEntity.ok(foundUser);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
        
    }

    @PostMapping(value="", produces = "application/json")
    public ResponseEntity<Object> saveUser(@RequestBody UserRequest reqUser) {
        try {
            User createdUser = UsersManager.getInstance().saveUser(reqUser);
            return ResponseEntity.ok(createdUser);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
    
}
