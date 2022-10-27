package br.com.audax.projeto.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private LocalDate registeredAt;

    public User(UUID uuid, String username, String password, LocalDate registeredAt){
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.registeredAt = registeredAt;
    }
}
