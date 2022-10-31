package br.com.audax.projeto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorReponseDTO {
    private String message;
    private DetailsResponseDTO details;
}
