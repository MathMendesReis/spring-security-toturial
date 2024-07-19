package com.maths.app.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.maths.app.domain.auth.application.service.AuthLoginUseCase;
import com.maths.app.domain.auth.enterprise.dto.AuthenticationDTO;
import com.maths.app.domain.auth.enterprise.model.User;
import com.maths.app.infra.security.TokenService;

@ExtendWith(MockitoExtension.class)
public class AuthLoginUseCaseTeste {
    @InjectMocks
    private AuthLoginUseCase sut;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    private AuthenticationDTO mockData;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockData = mock(AuthenticationDTO.class);
        when(mockData.login()).thenReturn("testUser");
        when(mockData.password()).thenReturn("testPass");
    }

    @Test
    @DisplayName("Must be able to authenticate a user")
    public void must_be_able_to_authenticate_a_user() {
        UsernamePasswordAuthenticationToken authentication = mock(UsernamePasswordAuthenticationToken.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        User user = mock(User.class); // Supondo que User seja uma classe que representa o usuário autenticado
        when(authentication.getPrincipal()).thenReturn(user);

        var result = sut.execute(mockData);

        assertTrue(result.isRight()); // Verifica se o resultado é um Right
        assertNotNull(result.getRightValue());
    }

    @Test
    @DisplayName("Should fail to authenticate a user")
    public void should_fail_to_authenticate_a_user() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));
        var result = sut.execute(mockData);
        assertTrue(result.isLeft());

    }

}
