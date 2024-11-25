package pororoAndFriends.ToyProject01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pororoAndFriends.ToyProject01.domain.Article;
import pororoAndFriends.ToyProject01.dto.AddArticleRequest;
import pororoAndFriends.ToyProject01.dto.ArticleResponse;
import pororoAndFriends.ToyProject01.dto.UpdateArticleRequest;
import pororoAndFriends.ToyProject01.service.BlogService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {

        Article saved = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {

        List<ArticleResponse> articles = blogService.findAll()
                .stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok().body(articles);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/articles/{id}")
    public Article findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return article;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/articles/{id}")
    public void deleteArticle(@PathVariable long id) {
        blogService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/articles/{id}")
    public Article deleteArticle(@PathVariable long id,
                              @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return updatedArticle;
    }
}
