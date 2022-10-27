package br.com.audax.projeto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.audax.projeto.entities.User;
import br.com.audax.projeto.exceptions.NotFoundException;
import br.com.audax.projeto.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User cadastrarUser(User novoUser){
        if(novoUser.getUsername().length() < 3 && novoUser.getUsername().length() > 150) throw new RuntimeException("O username não pode ter menos que 3 caracteres e não pode ter mais que 150");
        if(novoUser.getPassword().length() >= 8) throw new RuntimeException("A senha deve conter mais que 8 caracteres");
        novoUser.setRegisteredAt(LocalDate.now());
        return this.userRepository.save(novoUser);
    }

    public List<User> obterUsers() {
        return this.userRepository.findAll();
    }

    public User buscarUserPorId(UUID id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("Produto de id: " + id + " não foi encontrado!"));
    }

    public User atualizarProduto(UUID id, User userAtualizado) {
        User user = this.buscarUserPorId(id);

        if(userAtualizado.getUsername().length() < 3 && userAtualizado.getUsername().length() > 150) throw new RuntimeException("O username não pode ter menos que 3 caracteres e não pode ter mais que 150");
        if(userAtualizado.getPassword().length() >= 8) throw new RuntimeException("A senha deve conter mais que 8 caracteres");

        user.setUsername(userAtualizado.getUsername());
        user.setPassword(userAtualizado.getPassword());
        user.setRegisteredAt(LocalDate.now());

        return this.userRepository.save(user);
    }

    public void deletarUser(UUID id) {
        this.buscarUserPorId(id);
        this.userRepository.deleteById(id);
    }
}
