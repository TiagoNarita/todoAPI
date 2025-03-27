package com.example.demo.controllers.dto.in;

import com.example.demo.infrastructure.enums.UserRole;

public record RegisterDTO (String login, String password, UserRole role){
}
