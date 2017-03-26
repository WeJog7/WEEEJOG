package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.impl.DataSourceProvider;
import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class ArticleDaoImpl {
	
	public List<Article> ListArticleToDo(){
		List<Article> article = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT * FROM article ")){
			while ( resultSet.next()){
				article.add(new Article(
						resultSet.getInt("idarticle"),
						resultSet.getString("nomarticle"),
						resultSet.getString("contenuarticle"),					
						resultSet.getString("lien"),
						resultSet.getLong("userCreator")));
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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `article`(`nomarticle`,`contenuarticle`,`lien`,`userCreator`)VALUES(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1,newArticle.getNomarticle());
		statement.setString(2,newArticle.getContenuarticle());
	    statement.setString(3,newArticle.getLien());
	    statement.setLong(4,newArticle.getUserCreatorId());
		
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

	public void supprimerarticleadmin(long idarticle) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  article WHERE idarticle=?")){
			statement.setLong(1, idarticle);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}
	

}
