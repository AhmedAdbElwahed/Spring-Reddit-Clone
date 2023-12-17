package com.ahmed.spring_reddit_clone.controller;


import com.ahmed.spring_reddit_clone.dto.AuthenticationResponse;
import com.ahmed.spring_reddit_clone.dto.LoginRequest;
import com.ahmed.spring_reddit_clone.dto.RefreshTokenRequest;
import com.ahmed.spring_reddit_clone.dto.RegisterRequest;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.service.AuthService;
import com.ahmed.spring_reddit_clone.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private RefreshTokenService refreshTokenService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable(name = "token") String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<?> refreshToken(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
        } catch (SpringRedditException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh token deleted successfully");
    }

}
