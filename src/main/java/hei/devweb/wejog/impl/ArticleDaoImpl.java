package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.impl.DataSourceProvider;
import hei.devweb.wejog.entities.Article;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class ArticleDaoImpl {

	private Article mapperVersArticle(ResultSet resultSet) throws SQLException {

		return new Article (resultSet.getLong("idArticle"),
				resultSet.getString("name"),
				resultSet.getDate("postDate").toLocalDate(),
				resultSet.getString("content"),					
				resultSet.getString("link"),
				resultSet.getLong("creatorId"),
				resultSet.getString("prenom"),
				resultSet.getString("picturePath"));
	}


	public List<Article> ListArticleToDo(LocalDate limitatedDate){
		List<Article> article = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idArticle, name, postDate, content, link, creatorId, prenom, "
					+ "picturePath "
					+ "FROM article "
					+ "INNER JOIN users ON article.creatorId=users.idusers "
					+ "WHERE postDate>=? AND display "
					+ "ORDER BY postDate DESC, idArticle DESC")){
				statement.setDate(1, Date.valueOf(limitatedDate));
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					article.add(mapperVersArticle(resultSet));
				}
				statement.close();
				connection.close();
			}}
		catch (SQLException e){
			e.printStackTrace();
		}
		return article;
	}


	public Article addArticle(Article newArticle){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `article`(`name`, postDate,`content`,"
					+ "`link`,`creatorId`)VALUES(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,newArticle.getName());
			statement.setDate(2, Date.valueOf(newArticle.getPostDate()));
			statement.setString(3,newArticle.getContent());
			statement.setString(4,newArticle.getLink());
			statement.setLong(5,newArticle.getCreatorId());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newArticle.setIdArticle(resultSet.getLong(1));
			}
			statement.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return newArticle;
	}


	public void deleteArticle(long idarticle) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE article SET display=? WHERE idArticle=?")){
				statement.setBoolean(1, false);
				statement.setLong(2, idarticle);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}


	public Article getArticle(Long idarticle){
		Article article = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idArticle, name, postDate, content, link, creatorId, prenom, "
					+ "picturePath "
					+ "FROM article INNER JOIN users ON article.creatorId=users.idusers "
					+ "WHERE idArticle=?")){
				statement.setLong(1, idarticle);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					article = mapperVersArticle(resultSet);
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return article;		
	}
}
