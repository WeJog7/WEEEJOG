package hei.devweb.wejog.managers;

import java.time.LocalDate;
import java.util.List;

import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.impl.ArticleDaoImpl;



public class ArticleService {

	private ArticleDaoImpl articleDao = new ArticleDaoImpl();

	private static class ArticleServiceHolder {
		private static ArticleService instance = new ArticleService();
	}

	public static ArticleService getInstance() {
		return ArticleServiceHolder.instance;
	}

	private ArticleService() {
	}

	public  List<Article> ListArticleToDo(LocalDate limitatedDate){
		return articleDao.ListArticleToDo(limitatedDate);

	}

	public void addArticle(Article newArticle) {
		articleDao.addArticle(newArticle);

	} 

	public void supprimerarticleadmin(Long idarticle) {	
		articleDao.supprimerarticleadmin(idarticle);
	}
	
	public Article getArticle(Long idarticle){
		return articleDao.getArticle(idarticle);
	}

}



