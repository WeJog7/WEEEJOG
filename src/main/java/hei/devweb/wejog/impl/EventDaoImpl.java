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
	
	public List<Event> ListJoueurToDo(){
		List<Event> joueur = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement statement = connection.createStatement();
				 ResultSet resultSet= statement.executeQuery("SELECT * FROM event  ")){
			while ( resultSet.next()){
				joueur.add(new Event(resultSet.getInt("idjoueur"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("poste")) );
			}
			statement.close();
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return joueur;
	}

}
