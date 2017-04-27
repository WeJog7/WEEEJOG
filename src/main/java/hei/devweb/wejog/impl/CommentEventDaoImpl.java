package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.entities.CommentEvent;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class CommentEventDaoImpl {
	
	private CommentEvent mapperVersCommentEvent(ResultSet resultSet) throws SQLException {

		return new CommentEvent (resultSet.getLong("idComment"),
				resultSet.getLong("idEvent"),
				resultSet.getLong("creatorId"),
				resultSet.getString("postDateTime"),
				resultSet.getString("content"),					
				resultSet.getString("prenom"),
				resultSet.getString("picturePath"));
	}
	
	
	public List<CommentEvent> ListCommentEventToDo(Long idEvent){
		List<CommentEvent> commentList = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idComment, idEvent, creatorId, postDateTime, content, prenom, "
					+ "picturePath "
					+ "FROM comments "
					+ "INNER JOIN users ON comments.creatorId=users.idusers "
					+ "WHERE idEvent=? AND display "
					+ "ORDER BY postDateTime ASC, idComment ASC")){
				statement.setLong(1, idEvent);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					commentList.add(mapperVersCommentEvent(resultSet));
				}
				statement.close();
				connection.close();
			}}
		catch (SQLException e){
			e.printStackTrace();
		}
		return commentList;
	}
	
	
	public CommentEvent addCommentEvent(CommentEvent newCommentEvent){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `comments`(`idEvent`, creatorId, postDateTime,`content`)VALUES(?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1,newCommentEvent.getIdEvent());
			statement.setLong(2,newCommentEvent.getCreatorId());
			statement.setString(3, newCommentEvent.getPostDateTime());
			statement.setString(4,newCommentEvent.getContent());
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newCommentEvent.setIdComment(resultSet.getLong(1));
			}
			statement.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return newCommentEvent;
	}
	
	
	public void deleteCommentEvent(long idComment) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE comments SET display=? WHERE idComment=?")){
				statement.setBoolean(1, false);
				statement.setLong(2, idComment);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}
	
	
	public CommentEvent getCommentEvent(Long idComment){
		CommentEvent comment = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idComment, idEvent, creatorId, postDateTime, content, prenom, "
					+ "picturePath "
					+ "FROM comments "
					+ "INNER JOIN users ON comments.creatorId=users.idusers "
					+ "WHERE idComment=?")){
				statement.setLong(1, idComment);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					comment = mapperVersCommentEvent(resultSet);
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return comment;		
	}

}
