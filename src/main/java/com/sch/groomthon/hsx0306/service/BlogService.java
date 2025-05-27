package com.sch.groomthon.hsx0306.service;

import com.sch.groomthon.hsx0306.DTO.AddArticleRequest;
import com.sch.groomthon.hsx0306.DTO.UpdateArticleRequest;
import com.sch.groomthon.hsx0306.domains.Article;
import com.sch.groomthon.hsx0306.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

  private final BlogRepository blogRepository;

  // 블로그 글 추가 메서드
  public Article save(AddArticleRequest request) {
    return blogRepository.save(request.toEntity());
  }

  public List<Article> findAll() {
    return blogRepository.findAll();
  }

  public Article findById(Long id) {
    return blogRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("not found: " + id));
  }

  public void delete(Long id) {
    blogRepository.deleteById(id);
  }

  @Transactional
  public Article update(long id, UpdateArticleRequest request) {
    Article article = blogRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

    article.update(request.getTitle(), request.getContent());

    return article;
  }
}