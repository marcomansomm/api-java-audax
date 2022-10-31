package br.com.audax.projeto.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleResponseDTO {
    
    private UUID uuid;
    private String title;
    private String resume;
    private String text;
    private String slug;
    private LocalDateTime registeredAt;
    private UserResponseDTO user;
}
