package com.sch.groomthon.hsx0306.service;


import com.sch.groomthon.hsx0306.DTO.AddArticleRequest;
import com.sch.groomthon.hsx0306.domains.Article;
import com.sch.groomthon.hsx0306.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

  private final BlogRepository blogRepository;

  // 블로그 글 추가 메서드
  public Article save(AddArticleRequest request) {
    return (Article) blogRepository.save(request.toEntity()); //Article 객체로 변환 후 저장
  }
}