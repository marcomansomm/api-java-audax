package br.com.audax.projeto.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractException extends RuntimeException {

    private String erro;
    private String message;

    public AbstractException(String erro, String message) {
        this.erro = erro;
        this.message = message;
    }
}
