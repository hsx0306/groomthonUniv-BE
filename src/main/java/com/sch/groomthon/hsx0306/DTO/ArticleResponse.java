package com.sch.groomthon.hsx0306.DTO;

import com.sch.groomthon.hsx0306.domains.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {

  private final String title;
  private final String content;

  public ArticleResponse(Article article) {
    this.title = article.getTitle();
    this.content = article.getContent();
  }
}