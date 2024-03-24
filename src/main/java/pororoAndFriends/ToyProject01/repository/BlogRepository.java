package pororoAndFriends.ToyProject01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pororoAndFriends.ToyProject01.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
