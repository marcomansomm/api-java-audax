package br.com.audax.projeto.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.audax.projeto.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID>{
    
    Optional<Article> findById(UUID id);
}
