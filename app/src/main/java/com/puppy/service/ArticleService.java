package com.puppy.service;

import com.google.common.collect.Lists;
import com.puppy.domain.Article;
import com.puppy.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Transactional(readOnly = true)
	public List<Article> list() {
		return Lists.newArrayList(articleRepository.findAll());
	}

	@Transactional(readOnly = false)
	public Article addArticle(String content) {
		Article article = new Article();
		article.setContent(content);
		return articleRepository.save(article);
	}
}
