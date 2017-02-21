package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.impl.DataSourceProvider;
import hei.devweb.wejog.entities.Article;

public class ArticleDaoImpl {
	
	public List<Article> ListArticleToDo(){
		List<Article> article = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT * FROM article ")){
			while ( resultSet.next()){
				article.add(new Article(
						resultSet.getInt("idarticle"),
						resultSet.getString("lieuarticle"),
						resultSet.getDate("datearticle").toLocalDate(),
						resultSet.getString("contenuarticle"),					
						resultSet.getString("lienevent"),
						resultSet.getInt("user")));
			}
			statement.close();
			connection.close();
		}}}
		catch (SQLException e){
			e.printStackTrace();
		
		}
				

				return article;
			}
		
	
	
	public Article addArticle(Article newArticle){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `article`(`nomarticle`,`dateartcile`,`contenuarticle`,`lien`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1,newArticle.getNomarticle());
		statement.setDate(2,Date.valueOf(newArticle.getDatearticle()));
		statement.setString(3,newArticle.getContenuarticle());
	    statement.setString(4,newArticle.getLien());
		
		
				statement.executeUpdate();
				
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) {
					newArticle.setIdarticle(resultSet.getInt(1));
					
				}
				statement.close();
				connection.close();
			}
			
 catch (SQLException e){
	e.printStackTrace();

 }
	return newArticle;
}


	

}
