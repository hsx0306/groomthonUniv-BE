package com.sch.groomthon.hsx0306.controller;

import com.sch.groomthon.hsx0306.DTO.AddArticleRequest;
import com.sch.groomthon.hsx0306.DTO.ArticleListViewResponse;
import com.sch.groomthon.hsx0306.DTO.ArticleViewResponse;
import com.sch.groomthon.hsx0306.domains.Article;
import com.sch.groomthon.hsx0306.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
  private final BlogService blogService;
  @GetMapping("/articles")
  public String getArticles(Model model) {
    List<ArticleListViewResponse> articles = blogService.findAll().stream()
        .map(ArticleListViewResponse::new)
        .toList();

    model.addAttribute("articles", articles);

    return "articleList";
  }

  @GetMapping("/articles/{id}")
  public String getArticle(@PathVariable Long id, Model model) {
    Article article = blogService.findById(id);
    model.addAttribute("article", new ArticleViewResponse(article));
    return "article";
  }

  @GetMapping("/new-article")
  public String newArticle(@RequestParam(required = false) Long id, Model model) {
    if (id == null) {
      model.addAttribute("article", new ArticleViewResponse());
    } else {
      Article article = blogService.findById(id);
      model.addAttribute("article", new ArticleViewResponse(article));
    }

    return "newArticle";
  }
}