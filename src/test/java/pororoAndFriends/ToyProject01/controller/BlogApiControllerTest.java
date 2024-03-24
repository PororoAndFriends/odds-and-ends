package pororoAndFriends.ToyProject01.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pororoAndFriends.ToyProject01.domain.Article;
import pororoAndFriends.ToyProject01.dto.AddArticleRequest;
import pororoAndFriends.ToyProject01.repository.BlogRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void mokMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    @DisplayName("저장")
    @Test
    void addArticle() throws Exception{
        String url = "/api/articles";
        String title = "title";
        String content = "content";
        AddArticleRequest userRequest = new AddArticleRequest(title, content);

        String requestBody = objectMapper.writeValueAsString(userRequest);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));

        result.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("전체 조회")
    @Test
    void findAllArticles() throws Exception {
        String url = "/api/articles";
        blogRepository.save(new Article("제목1", "내용1"));
        blogRepository.save(new Article("제목2", "내용2"));
        blogRepository.save(new Article("제목3", "내용3"));

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url));

        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("내용1"));
    }

    @DisplayName("id로 찾기")
    @Test
    void findById() throws Exception {
        String url = "/api/articles/{id}";
        Article article = new Article("제목1", "내용1");
        blogRepository.save(article);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url, article.getId()));

        result.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용1"));
    }

    @DisplayName("삭제")
    @Test
    void deleteArticle() throws Exception {
        String url = "/api/articles/{id}";
        Article article = new Article("제목1", "내용1");
        blogRepository.save(article);

        mockMvc.perform(MockMvcRequestBuilders.delete(url, article.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @DisplayName("업데이트")
    @Test
    void updateArticle() throws Exception {
        String url = "/api/articles/{id}";
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        blogRepository.save(article1);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(url, article1.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(article2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용2"));
    }

}