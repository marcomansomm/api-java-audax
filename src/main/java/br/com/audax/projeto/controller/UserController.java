package br.com.audax.projeto.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.audax.projeto.DTO.UserResponseDTO;
import br.com.audax.projeto.entities.User;
import br.com.audax.projeto.repository.UserRepository;
import br.com.audax.projeto.service.UserService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    private UserResponseDTO toUserResponseDTO(User User) {
        return this.modelMapper.map(User, UserResponseDTO.class);
    }

    @GetMapping
    public List<UserResponseDTO> obterUsers() {
        return this.userService.obterUsers()
                .stream()
                .map(this::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> cadastrarUser(@RequestBody User user){
        User novoUser = this.modelMapper.map(user, User.class);
        novoUser = this.userService.cadastrarUser(novoUser);
        return new ResponseEntity<>(toUserResponseDTO(novoUser), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> obterUserPorId(@PathVariable UUID uuid){
        User user = this.userService.buscarUserPorId(uuid);
        return new ResponseEntity<>(toUserResponseDTO(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarProduto(@PathVariable UUID uuid, @RequestBody UserResponseDTO user) {
        User userAtualizado = this.modelMapper.map(user, User.class);
        userAtualizado = this.userService.atualizarUser(uuid, userAtualizado);

        return new ResponseEntity<>(toUserResponseDTO(userAtualizado), HttpStatus.OK);
    }
}
