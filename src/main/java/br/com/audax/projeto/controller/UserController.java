package br.com.audax.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.audax.projeto.entities.User;
import br.com.audax.projeto.repository.UserRepository;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping
    public List<User> obterUsuarios(){
        return repository.findAll();
    }
}
