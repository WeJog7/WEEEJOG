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

	public List<Article> ListArticleToDo(LocalDate limitatedDate){
		List<Article> article = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE dateOfPost>=? ORDER BY dateOfPost DESC, "
					+ "idarticle DESC")){
					statement.setDate(1, Date.valueOf(limitatedDate));
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()){
						article.add(new Article(
								resultSet.getLong("idarticle"),
								resultSet.getString("nomarticle"),
								resultSet.getDate("dateOfPost").toLocalDate(),
								resultSet.getString("contenuarticle"),					
								resultSet.getString("lien"),
								resultSet.getLong("userCreator"),
								resultSet.getString("creatorFirstName")));
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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `article`(`nomarticle`,dateOfPost,`contenuarticle`,`lien`,`userCreator`,creatorFirstName)VALUES(?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,newArticle.getNomarticle());
			statement.setDate(2, Date.valueOf(newArticle.getDateOfPost()));
			statement.setString(3,newArticle.getContenuarticle());
			statement.setString(4,newArticle.getLien());
			statement.setLong(5,newArticle.getUserCreatorId());
			statement.setString(6, newArticle.getCreatorFirstName());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newArticle.setIdarticle(resultSet.getLong(1));

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

	private Article mapperVersArticle(ResultSet resultSet) throws SQLException {

		return new Article (resultSet.getLong("idarticle"),
				resultSet.getString("nomarticle"),
				resultSet.getDate("dateOfPost").toLocalDate(),
				resultSet.getString("contenuarticle"),					
				resultSet.getString("lien"),
				resultSet.getLong("userCreator"),
				resultSet.getString("creatorFirstName"));
	}

	public Article getArticle(Long idarticle){
		Article article = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE idarticle=?")){
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
