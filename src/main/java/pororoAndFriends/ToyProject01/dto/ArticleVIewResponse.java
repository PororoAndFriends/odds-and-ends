package pororoAndFriends.ToyProject01.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pororoAndFriends.ToyProject01.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleVIewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleVIewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
