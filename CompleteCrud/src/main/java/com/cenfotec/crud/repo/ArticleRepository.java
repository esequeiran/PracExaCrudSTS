package com.cenfotec.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.crud.domain.Article;

public interface ArticleRepository extends JpaRepository<Article,Long> {

}




