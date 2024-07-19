package com.maths.app.infra.http.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.app.domain.auth.application.service.AuthLoginUseCase;
import com.maths.app.domain.auth.enterprise.dto.AuthenticationDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class UserLogin {
    @Autowired
    private AuthLoginUseCase useCase;

    @PostMapping("/login")
    public ResponseEntity<?> handle(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var token = useCase.execute(data);
            return ResponseEntity.ok().body(token.getRightValue());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
