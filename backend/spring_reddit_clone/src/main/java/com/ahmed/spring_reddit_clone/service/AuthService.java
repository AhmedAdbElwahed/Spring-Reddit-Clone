package com.ahmed.spring_reddit_clone.service;


import com.ahmed.spring_reddit_clone.configuration.security.JwtUtils;
import com.ahmed.spring_reddit_clone.dto.AuthenticationResponse;
import com.ahmed.spring_reddit_clone.dto.LoginRequest;
import com.ahmed.spring_reddit_clone.dto.RefreshTokenRequest;
import com.ahmed.spring_reddit_clone.dto.RegisterRequest;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.model.NotificationEmail;
import com.ahmed.spring_reddit_clone.model.User;
import com.ahmed.spring_reddit_clone.model.VerificationToken;
import com.ahmed.spring_reddit_clone.repository.UserRepository;
import com.ahmed.spring_reddit_clone.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private MailService mailService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(NotificationEmail.builder()
                .subject("Please Activate Your Account")
                .recipient(user.getEmail())
                .body("""
                              Thank you for signing up to Spring Reddit please click on the below url to activate your account
                              : http://localhost:8080/api/auth/accountVerification/
                              """ + token)
                .build());
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken =
                verificationTokenRepository.findVerificationTokenByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));


    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new SpringRedditException(String.format("user %s not found", username)));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        String verificationTokenUUID = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(verificationTokenUUID);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return verificationTokenUUID;
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication =
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Date.from(Instant.now().plusMillis(jwtUtils.getJwtExpirationMs())))
                .username(loginRequest.getUsername())
                .build();
    }

    public User getCurrentUser() {
         return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token =  jwtUtils.generateJwtTokenByUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Date.from(Instant.now().plusMillis(jwtUtils.getJwtExpirationMs())))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
