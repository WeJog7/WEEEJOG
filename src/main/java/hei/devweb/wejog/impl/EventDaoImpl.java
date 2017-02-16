package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.impl.DataSourceProvider;

import hei.devweb.wejog.entities.Event;

public class EventDaoImpl {
	
	public List<Event> ListEventToDo(){
		List<Event> event = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement statement = connection.createStatement();
				 ResultSet resultSet= statement.executeQuery("SELECT * FROM event ")){
			while ( resultSet.next()){
				event.add(new Event(
						resultSet.getInt("idevent"),
						resultSet.getDate("dateevent").toLocalDate(),
						resultSet.getDate("horaireevent").toLocalDate(),
						resultSet.getDouble("dureeevent"),
						resultSet.getDouble("distanceevent"),
						resultSet.getString("lieuevent"),
						resultSet.getInt("usergestion"),
						resultSet.getInt("userparticipant")) );
			}
			statement.close();
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return event ;
	}

}
