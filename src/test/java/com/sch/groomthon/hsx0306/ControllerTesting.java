package com.sch.groomthon.hsx0306;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sch.groomthon.hsx0306.DTO.AddArticleRequest;
import com.sch.groomthon.hsx0306.domains.Article;
import com.sch.groomthon.hsx0306.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
public class ControllerTesting {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스

  @Autowired
  private WebApplicationContext context;

  @Autowired
  BlogRepository blogRepository;

  @BeforeEach // 테스트 실행 전 실행하는 메서드
  public void mockMvcSetUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .build();
    blogRepository.deleteAll();
  }

  @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
  @Test
  public void addArticle() throws Exception {
    // given
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);

    // 객체 JSON으로 직렬화
    final String requestBody = objectMapper.writeValueAsString(userRequest);

    // when
    // 설정한 내용을 바탕으로 요청 전송
    ResultActions result = mockMvc.perform((RequestBuilder) post(url)
        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
        .contentType(MediaType.valueOf(requestBody)));

    // then
    result.andExpect(status().isCreated());

    List<Article> articles = blogRepository.findAll();

    assertThat(articles.size()).isEqualTo(1); // 크기가 1인지 검증
    assertThat(articles.get(0).getTitle()).isEqualTo(title);
    assertThat(articles.get(0).getContent()).isEqualTo(content);
  }
}
