package br.com.audax.projeto.controller;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.audax.projeto.DTO.ExceptionResponseDTO;
import br.com.audax.projeto.exceptions.NotFoundException;

@ControllerAdvice
public class GlobalExceptionController {

    private ExceptionResponseDTO exceptionResponseDTO;

    // @ExceptionHandler(value = BadRequestException.class)
    // public ResponseEntity<ErrorMessage> badRequest(BadRequestException exception) {
    //     return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getErro(), exception.getDescricao()), HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> notFound(NotFoundException exception) {
        return new ResponseEntity<>();
    }
}