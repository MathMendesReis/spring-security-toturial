package com.maths.app.domain.auth.enterprise.dto;

import com.maths.app.domain.auth.enterprise.model.UserRole;
import lombok.NonNull;

public record RegisterDTO(
        @NonNull String login,
        @NonNull String password,
        @NonNull UserRole role) {

}
