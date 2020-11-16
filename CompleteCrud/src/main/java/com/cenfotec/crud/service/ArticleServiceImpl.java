package com.cenfotec.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.repo.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepository articleRepo;
	
	
	public void save(Article article) {
		articleRepo.save(article);
	}
	
}
