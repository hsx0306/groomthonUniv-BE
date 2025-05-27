package com.sch.groomthon.hsx0306.repository;


import com.sch.groomthon.hsx0306.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}