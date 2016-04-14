package com.puppy.web;

import com.puppy.domain.Article;
import com.puppy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Article> list() {
		return articleService.list();
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public Article saveArticle(@RequestParam String content) {
		return articleService.addArticle(content);
	}
}
