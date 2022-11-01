package br.com.audax.projeto.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.audax.projeto.entities.Article;
import br.com.audax.projeto.entities.User;
import br.com.audax.projeto.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repositoryArticle;
    
    @Autowired
    private UserService userService;

    private final String PREFIX_SLUG = "www.audax_projeto/";
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase();
    }

    public Article cadastrarArticle(Article novoArticle) {
        if(novoArticle.getTitle().length() < 30 || novoArticle.getTitle().length() > 70) throw new RuntimeException("O title tem que entre 30 e 150 caracters");
        if(novoArticle.getResume().length() < 50 || novoArticle.getResume().length() > 100) throw new RuntimeException("O resume tem que entre 50 e 100 caracters");
        if(novoArticle.getText().length() < 200) throw new RuntimeException("O text tem que no minimo 200 carcteres");
        
        String slug = this.toSlug(novoArticle.getTitle());
        User user = this.userService.buscarUserPorId(novoArticle.getUser().getUuid());
        
        novoArticle.setUser(user);
        novoArticle.setSlug(PREFIX_SLUG + slug);
        novoArticle.setRegisteredAt(LocalDateTime.now());

        return this.repositoryArticle.save(novoArticle);
    }

    public List<Article> obterArticle() {

        return this.repositoryArticle.findAll();
    }

    public Article buscarArticlePorId(UUID uuid) {
        Optional<Article> article = this.repositoryArticle.findById(uuid);
        
        return article.orElseThrow(() -> new RuntimeException("Article não encontrado"));
    }

    public Article atualizarArticle(UUID uuid, Article articleAtualizado) {
        Article article = this.buscarArticlePorId(uuid);

        if(articleAtualizado.getTitle().length() < 30 || articleAtualizado.getTitle().length() > 70) throw new RuntimeException("O title tem que entre 30 e 150 caracters");
        if(articleAtualizado.getResume().length() < 50 || articleAtualizado.getResume().length() > 100) throw new RuntimeException("O resume tem que entre 50 e 100 caracters");
        if(articleAtualizado.getText().length() < 200) throw new RuntimeException("O text tem que no minimo 200 carcteres");

        String slug = this.toSlug(articleAtualizado.getTitle());
        User user = this.userService.buscarUserPorId(articleAtualizado.getUser().getUuid());

        article.setTitle(articleAtualizado.getTitle());
        article.setResume(articleAtualizado.getResume());
        article.setText(articleAtualizado.getText());
        articleAtualizado.setSlug(PREFIX_SLUG + slug);
        article.setRegisteredAt(LocalDateTime.now());
        articleAtualizado.setUser(user);

        return this.repositoryArticle.save(article);
    }

    public void deletarArticle(UUID uuid) {
        this.buscarArticlePorId(uuid);
        this.repositoryArticle.deleteById(uuid);
    }
}
