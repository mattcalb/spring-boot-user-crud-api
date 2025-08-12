package com.mattcalb.spring_boot_crud.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @NotBlank(message = "Email cannot be null.")
        @Email(message = "Invalid email format.")
        String email,

        @NotBlank(message = "Name cannot be null.")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
        String name
) {}
