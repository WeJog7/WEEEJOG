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
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class EventDaoImpl {
		
	private Event mapperVersEvent(ResultSet resultSet) throws SQLException {

		return new Event (resultSet.getLong("idEvent"),
				resultSet.getDate("date").toLocalDate(),
				resultSet.getString("timeAsString"),
				resultSet.getString("momentOfTheDay"),
				resultSet.getDouble("duration"),
				resultSet.getDouble("distance"),
				resultSet.getString("place"),
				resultSet.getDouble("latitude"),
				resultSet.getDouble("longitude"),
				resultSet.getLong("creatorId"),
				resultSet.getString("details"),
				resultSet.getString("prenom"),
				resultSet.getString("picturePath"));
	}
	

	public List<Event> ListEventToDo(Date todayDate, long idusers){
		List<Event> event = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, picturePath, idEvent, date, timeAsString, "
					+ "momentOfTheDay, duration, distance, place, latitude, longitude, creatorId, details "
					+ "FROM event "
					+ "INNER JOIN users ON event.creatorId=users.idusers "
					+ "where display AND (date>=? AND creatorId!=?) "
					+ "ORDER BY date ASC, momentOfTheDay, hour ASC, minutes ASC")){
				statement.setDate(1, todayDate);
				statement.setLong(2, idusers);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					event.add(mapperVersEvent(resultSet));
				}
				statement.close();
				connection.close();
			}}
		catch (SQLException e){
			e.printStackTrace();

		}
		return event;
	}
	
	
	public List<Event> listEventsSubscribed(long idusers, Date todayDate){
		List<Event> event = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT req.prenom AS prenom, "
					+ "req.picturePath AS picturePath, event.idEvent, date, timeAsString, "
					+ "momentOfTheDay, duration, distance, place, latitude, longitude, creatorId, details "
					+ "FROM event "
					+ "INNER JOIN participant ON event.idEvent=participant.idevent "
					+ "INNER JOIN (SELECT event.idEvent AS idEvent, users.idusers AS idusers, users.prenom AS prenom, "
					+ "users.picturePath AS picturePath "
					+ "FROM users INNER JOIN event "
					+ "ON users.idusers = event.creatorId) AS req "
					+ "ON req.idEvent = event.idEvent "
					+ "where participant.idusers=? AND event.display AND (event.date>=? AND event.creatorId!=?) "
					+ "ORDER BY date ASC, momentOfTheDay, hour ASC, minutes ASC")){
				statement.setLong(1, idusers);
				statement.setDate(2, todayDate);
				statement.setLong(3, idusers);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()){
					event.add(mapperVersEvent(resultSet));
				}
				statement.close();
				connection.close();
			}}
		catch (SQLException e){
			e.printStackTrace();

		}
		return event;
	}
	
	
	public List<Event> ListmyEventAdministrated(long idusers, Date todayDate){
		List<Event> event = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, picturePath, idEvent, date, timeAsString, "
					+ "momentOfTheDay, duration, distance, place, latitude, longitude, creatorId, details "
					+ "FROM event "
					+ "INNER JOIN users ON event.creatorId=users.idusers "
					+ "WHERE creatorId=? and (display AND date>=?) "
					+ "ORDER BY date ASC, momentOfTheDay, hour ASC, minutes ASC")){
				statement.setLong(1, idusers);
				statement.setDate(2, todayDate);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					event.add(mapperVersEvent(resultSet));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return event;		
	}


	public Event addEvent(Event newEvent){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `event`(date, timeAsString, hour, minutes,momentOfTheDay,"
					+ "`duration`,`distance`,`place`,latitude,longitude,`creatorId`,details) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1,Date.valueOf(newEvent.getDate()));
			statement.setString(2, newEvent.getTimeAsString());
			statement.setInt(3,newEvent.getHour());
			statement.setInt(4, newEvent.getMinutes());
			statement.setString(5,newEvent.getMomentOfTheDay());
			statement.setDouble(6,newEvent.getDuration());
			statement.setDouble(7,newEvent.getDistance());
			statement.setString(8,newEvent.getPlace());
			statement.setDouble(9,newEvent.getLatitude());
			statement.setDouble(10,newEvent.getLongitude());
			statement.setLong(11,newEvent.getCreatorId());
			statement.setString(12,newEvent.getDetails());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newEvent.setIdEvent(resultSet.getLong(1));
			}
			statement.close();
			connection.close();
		}

		catch (SQLException e){
			e.printStackTrace();
		}
		return newEvent;
	}

	public void deleteEvent(long idevent) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE event SET display=? WHERE idEvent=?")){
				statement.setBoolean(1, false);
				statement.setLong(2, idevent);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}

	
	public Event getEvent(long idevent, LocalDate todayDate){
		Event event = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, picturePath, idEvent, date, timeAsString, "
					+ "momentOfTheDay, duration, distance, place, latitude, longitude, creatorId, details "
					+ "FROM event "
					+ "INNER JOIN users ON event.creatorId=users.idusers "
					+ "WHERE idEvent=? AND date>=? AND display")){
				statement.setLong(1, idevent);
				statement.setDate(2, Date.valueOf(todayDate));
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
	
	
	public Event getEvent(long idevent){
		Event event = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, picturePath, idEvent, date, timeAsString, "
					+ "momentOfTheDay, duration, distance, place, latitude, longitude, creatorId, details "
					+ "FROM event "
					+ "INNER JOIN users ON event.creatorId=users.idusers "
					+ "WHERE idEvent=? AND display ")){
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
