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
				try(ResultSet resultSet = statement.executeQuery("SELECT * FROM event where affiche")){
					while ( resultSet.next()){
						event.add(new Event(
								resultSet.getInt("idevent"),
								resultSet.getDate("dateevent").toLocalDate(),
								resultSet.getString("horaireevent"),
								resultSet.getString("momentOfTheDay"),
								resultSet.getDouble("dureeevent"),
								resultSet.getDouble("distanceevent"),
								resultSet.getString("lieuevent"),
								resultSet.getLong("user1"),
								resultSet.getString("userGestionFirstName")));
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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `event`(`dateevent`,`horaireevent`,momentOfTheDay,"
					+ "`dureeevent`,`distanceevent`,`lieuevent`,`user1`,userGestionFirstName)VALUES(?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1,Date.valueOf(newEvent.getDateevent()));
			statement.setString(2,newEvent.getHoraireevent());
			statement.setString(3,newEvent.getMomentOfTheDay());
			statement.setDouble(4,newEvent.getDureeevent());
			statement.setDouble(5,newEvent.getDistanceevent());
			statement.setString(6,newEvent.getLieuevent());
			statement.setLong(7,newEvent.getUsergestion());
			statement.setString(8,newEvent.getUserGestionFirstName());


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
			try(PreparedStatement statement = connection.prepareStatement("UPDATE event SET affiche=? WHERE idevent=?")){
				statement.setBoolean(1, false);
				statement.setLong(2, idevent);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}



	public List<Event> ListmyEvent(long idusers ){
		List<Event> event = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM event WHERE user1=? and affiche")){
				statement.setLong(1, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					event.add(new Event(
							resultSet.getInt("idevent"),
							resultSet.getDate("dateevent").toLocalDate(),
							resultSet.getString("horaireevent"),
							resultSet.getString("momentOfTheDay"),
							resultSet.getDouble("dureeevent"),
							resultSet.getDouble("distanceevent"),
							resultSet.getString("lieuevent"),
							resultSet.getLong("user1"),
							resultSet.getString("userGestionFirstName")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return event;		
	}
	
private Event mapperVersEvent(ResultSet resultSet) throws SQLException {
		
		return new Event (resultSet.getInt("idevent"),
				resultSet.getDate("dateevent").toLocalDate(),
				resultSet.getString("horaireevent"),
				resultSet.getString("momentOfTheDay"),
				resultSet.getDouble("dureeevent"),
				resultSet.getDouble("distanceevent"),
				resultSet.getString("lieuevent"),
				resultSet.getLong("user1"),
				resultSet.getString("userGestionFirstName"));
	}

	public Event getEvent(long idevent ){
		Event event = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM event WHERE idevent=?")){
				statement.setLong(1, idevent);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					event = mapperVersEvent(resultSet);
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return event;		
	}
}
