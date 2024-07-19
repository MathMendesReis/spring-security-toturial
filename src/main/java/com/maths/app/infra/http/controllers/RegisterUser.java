package com.maths.app.infra.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.app.domain.auth.application.service.RegisterUserUseCase;
import com.maths.app.domain.auth.enterprise.dto.RegisterDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class RegisterUser {
    @Autowired
    private RegisterUserUseCase useCase;

    @PostMapping("/register")
    public ResponseEntity<?> handle(@RequestBody @Valid RegisterDTO data) {
        try {
            var result = useCase.execute(data).getRightValue();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
}
