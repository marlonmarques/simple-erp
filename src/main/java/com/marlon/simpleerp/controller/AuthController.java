package com.marlon.simpleerp.controller;

import com.marlon.simpleerp.dto.LoginDTO;
import com.marlon.simpleerp.dto.RegisterDTO;
import com.marlon.simpleerp.dto.TokenDTO;
import com.marlon.simpleerp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO login) {
        TokenDTO token = authService.login(login);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@Valid @RequestBody RegisterDTO register) {
        TokenDTO token = authService.register(register);
        return ResponseEntity.ok(token);
    }
}