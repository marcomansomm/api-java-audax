package br.com.audax.projeto.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends AbstractException{
    
    public NotFoundException(String descricao) {
        super("Not Found", descricao);
    }
}
