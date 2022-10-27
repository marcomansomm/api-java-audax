package br.com.audax.projeto.entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", updatable = false, unique = true, nullable = false)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String title;
    private String resume;
    private String text;
    private String slug;
    private LocalDate registeredAt;

    public Article(User user, UUID uuid, String title, String resume, String text, String slug, LocalDate registeredAt){
        this.user = user;
        this.uuid = uuid;
        this.title = title;
        this.resume = resume;
        this.text = text;
        this.slug = slug;
        this.registeredAt = registeredAt;
    }
}
