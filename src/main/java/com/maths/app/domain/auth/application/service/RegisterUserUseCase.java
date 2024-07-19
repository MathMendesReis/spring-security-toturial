package com.maths.app.domain.auth.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maths.app.core.either.Either;
import com.maths.app.core.either.Left;
import com.maths.app.core.either.Right;
import com.maths.app.domain.auth.application.repositories.UserRepository;
import com.maths.app.domain.auth.enterprise.dto.RegisterDTO;
import com.maths.app.domain.auth.enterprise.model.User;

@Service
public class RegisterUserUseCase {
    @Autowired
    private UserRepository repository;

    public Either<String, User> execute(RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null)
        return new Left<>("Usuário já existe");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return new Right<>(newUser);

    }
}
