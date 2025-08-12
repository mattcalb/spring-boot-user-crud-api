package com.mattcalb.spring_boot_crud.dtos;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDto(UUID id,
                              String email,
                              String name,
                              LocalDate birthDate,
                              Instant createdAt,
                              Instant updatedAt) {
}
