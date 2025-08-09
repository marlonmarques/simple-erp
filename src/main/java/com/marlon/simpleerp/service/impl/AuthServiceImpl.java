package com.marlon.simpleerp.service.impl;

import com.marlon.simpleerp.dto.LoginDTO;
import com.marlon.simpleerp.dto.RegisterDTO;
import com.marlon.simpleerp.dto.TokenDTO;
import com.marlon.simpleerp.model.Usuario;
import com.marlon.simpleerp.repository.UsuarioRepository;
import com.marlon.simpleerp.security.JwtUtil;
import com.marlon.simpleerp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDTO login(LoginDTO login) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.email(), login.senha())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            String token = jwtUtil.gerarToken(
                    usuario.getEmail(),
                    usuario.getNome(),
                    usuario.getPerfis().get(0)
            );

            return new TokenDTO(
                    token,
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getPerfis().get(0),
                    usuario.getId()
            );

        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }
    }

    @Override
    public TokenDTO register(RegisterDTO register) {
        if (usuarioRepository.findByEmail(register.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(register.nome());
        usuario.setEmail(register.email());
        usuario.setSenha(passwordEncoder.encode(register.senha()));
        usuario.getPerfis().add("USER"); // Pode ser "ADMIN" conforme necessidade

        usuarioRepository.save(usuario);

        String token = jwtUtil.gerarToken(usuario.getEmail(), usuario.getNome(), "USER");

        return new TokenDTO(
                token,
                usuario.getNome(),
                usuario.getEmail(),
                "USER",
                usuario.getId()
        );
    }
}