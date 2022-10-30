package br.com.audax.projeto.exceptions;

public class NotFoundException extends AbstractException{
    
    public NotFoundException(String descricao) {
        super("Not Found", descricao);
    }
}
