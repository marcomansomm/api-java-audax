package br.com.audax.projeto.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.audax.projeto.DTO.ArticleResponseDTO;
import br.com.audax.projeto.entities.Article;
import br.com.audax.projeto.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    private ArticleResponseDTO toArticleResponseDTO(Article article) {
        return this.modelMapper.map(article, ArticleResponseDTO.class);
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> cadastrarArticle(@Validated @RequestBody ArticleResponseDTO article){
        Article novoArticle = this.modelMapper.map(article, Article.class);
        novoArticle = this.articleService.cadastrarArticle(novoArticle);
        return new ResponseEntity<>(toArticleResponseDTO(novoArticle), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ArticleResponseDTO> obterArticle() {
        return this.articleService.obterArticle()
                .stream()
                .map(this::toArticleResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ArticleResponseDTO> obterArticlePorId(@PathVariable UUID uuid) {
        Article article = this.articleService.buscarArticlePorId(uuid);
        return new ResponseEntity<>(toArticleResponseDTO(article), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ArticleResponseDTO> atualizarArticle(@PathVariable  UUID uuid, @RequestBody ArticleResponseDTO article) {
        Article articleAtualizado = this.modelMapper.map(article, Article.class);
        articleAtualizado = this.articleService.atualizarArticle(uuid, articleAtualizado);

        return new ResponseEntity<>(toArticleResponseDTO(articleAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deletarArticle(@PathVariable UUID uuid) {
        this.articleService.deletarArticle(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
