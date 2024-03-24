package pororoAndFriends.ToyProject01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pororoAndFriends.ToyProject01.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){
        return new Article(title, content);
    }

}
