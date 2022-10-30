package br.com.audax.projeto.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDTO {
    private UUID uuid;
    private String username;
    private LocalDateTime registeredAt;
}
