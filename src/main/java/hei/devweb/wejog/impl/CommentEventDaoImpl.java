package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.entities.CommentEvent;

public class CommentEventDaoImpl {
	
	private CommentEvent mapperVersCommentEvent(ResultSet resultSet) throws SQLException {

		return new CommentEvent (resultSet.getLong("idComment"),
				resultSet.getLong("idEvent"),
				resultSet.getLong("creatorId"),
				resultSet.getDate("postDate").toLocalDate(),
				resultSet.getString("content"),					
				resultSet.getString("prenom"),
				resultSet.getString("picturePath"));
	}
	
	
	public List<CommentEvent> ListCommentEventToDo(Long idEvent){
		List<CommentEvent> commentList = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idComment, idEvent, creatorId, postDate, content, prenom, "
					+ "picturePath "
					+ "FROM comments "
					+ "INNER JOIN users ON comments.creatorId=users.idusers "
					+ "WHERE idEvent=? "
					+ "ORDER BY postDate ASC, idComment ASC")){
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

}
