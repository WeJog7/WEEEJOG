package hei.devweb.wejog.managers;

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

	public  List<Article> ListArticleToDo(){
		return articleDao.ListArticleToDo();
		
	}

	
	public void addArticle(Article newArticle) {
		articleDao.addArticle(newArticle);
		
	} 

}



