package com.maths.app.domain.auth.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.maths.app.core.either.Either;
import com.maths.app.core.either.Right;
import com.maths.app.domain.auth.enterprise.dto.AuthenticationDTO;
import com.maths.app.domain.auth.enterprise.dto.LoginResponseDTO;
import com.maths.app.domain.auth.enterprise.model.User;
import com.maths.app.infra.security.TokenService;


@Service
public class AuthLoginUseCase {
    @Autowired
    private AuthenticationManager authenticationManager;
  
    @Autowired
    private TokenService tokenService;
    
    public Either<?, LoginResponseDTO> execute(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new Right<>(new LoginResponseDTO(token));

    }
}
