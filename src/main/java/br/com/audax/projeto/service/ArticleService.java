package br.com.audax.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.audax.projeto.entities.Article;
import br.com.audax.projeto.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository repositoryArticle;

    // public Article cadastrarArticle(Article novoArticle){
    //     if(novoArticle.getTitle().length() < 30 )
    //     return this.repositoryArticle.save(novoArticle);
    // }
}
