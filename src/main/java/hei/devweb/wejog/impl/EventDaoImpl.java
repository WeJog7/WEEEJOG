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
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class EventDaoImpl {
	
	public List<Event> ListEventToDo(){
		List<Event> event = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT * FROM event ")){
			while ( resultSet.next()){
				event.add(new Event(
						resultSet.getInt("idevent"),
						resultSet.getDate("dateevent").toLocalDate(),
						resultSet.getDate("horaireevent").toLocalDate(),
						resultSet.getDouble("dureeevent"),
						resultSet.getDouble("distanceevent"),
						resultSet.getString("lieuevent"),
						resultSet.getLong("user1")));
			}
			statement.close();
			connection.close();
		}}}
		catch (SQLException e){
			e.printStackTrace();
		
		}
				

				return event;
			}
		
	
	
	public Event addEvent(Event newEvent){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `event`(`dateevent`,`horaireevent`,`dureeevent`,`distanceevent`,`lieuevent`,`user1`)VALUES(?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
		statement.setDate(1,Date.valueOf(newEvent.getDateevent()));
		statement.setDate(2,Date.valueOf(newEvent.getHoraireevent()));
		statement.setDouble(3,newEvent.getDureeevent());
	    statement.setDouble(4,newEvent.getDistanceevent());
	    statement.setString(5,newEvent.getLieuevent());
	    statement.setLong(6,newEvent.getUsergestion());
	    
		
		
				statement.executeUpdate();
				
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next()) {
					newEvent.setIdevent(resultSet.getInt(1));
					
				}
				statement.close();
				connection.close();
			}
			
 catch (SQLException e){
	e.printStackTrace();

 }
	return newEvent;
}

	public void supprimereventadmin(long idevent) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  event WHERE idevent=?")){
			statement.setLong(1, idevent);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}

	

	public List<Event> ListmyEvent(){
		List<Event> event = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT * FROM event  ")){
			while ( resultSet.next()){
				event.add(new Event(
						resultSet.getInt("idevent"),
						resultSet.getDate("dateevent").toLocalDate(),
						resultSet.getDate("horaireevent").toLocalDate(),
						resultSet.getDouble("dureeevent"),
						resultSet.getDouble("distanceevent"),
						resultSet.getString("lieuevent"),
						resultSet.getLong("user1")));
			}
			
			statement.close();
			connection.close();
		}}}
		catch (SQLException e){
			e.printStackTrace();
		
		}
				

				return event;
			}
		
}
