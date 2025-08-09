package com.marlon.simpleerp.service;

import com.marlon.simpleerp.dto.LoginDTO;
import com.marlon.simpleerp.dto.RegisterDTO;
import com.marlon.simpleerp.dto.TokenDTO;

public interface AuthService {
    TokenDTO login(LoginDTO login);
    TokenDTO register(RegisterDTO register);
}