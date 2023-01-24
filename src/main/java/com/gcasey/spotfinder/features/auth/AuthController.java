package com.gcasey.spotfinder.features.auth;

import com.gcasey.spotfinder.features.auth.dtos.AuthenticationResponse;
import com.gcasey.spotfinder.features.auth.dtos.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthenticationResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new AuthenticationResponse(
                authService.register(registerRequest.email(), registerRequest.username(), registerRequest.password())
        );
    }

}
