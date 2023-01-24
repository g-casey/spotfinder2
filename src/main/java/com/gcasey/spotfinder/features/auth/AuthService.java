package com.gcasey.spotfinder.features.auth;

import com.gcasey.spotfinder.data.user.User;
import com.gcasey.spotfinder.data.user.UserRepository;
import com.gcasey.spotfinder.infrastructure.exceptions.CustomException;
import com.gcasey.spotfinder.infrastructure.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String register(String email, String username, String password) {
        User user = new User(
                email,
                username,
                passwordEncoder.encode(password)
        );
        userRepository.save(user);
        return jwtService.generateToken(email);
    }

    public void login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
            throw new CustomException("Incorrect email or password", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        if (!user.isEnabled()) {
            throw new CustomException("User is not enabled", HttpStatus.BAD_REQUEST);
        }
    }
}
