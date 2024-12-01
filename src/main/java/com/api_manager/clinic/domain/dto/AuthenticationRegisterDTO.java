package com.api_manager.clinic.domain.dto;

import com.api_manager.clinic.domain.entity.UserRole;

public record AuthenticationRegisterDTO(String login, String password, UserRole userRole) {
}
