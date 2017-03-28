package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hei.devweb.wejog.entities.Participant;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class ParticipantDaoImpl {
	
	public List<Participant> ListEvenementsInscrits(long idusers ){
		List<Participant> participant = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM participant WHERE idusers=? ")){
				statement.setLong(1, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					participant.add(new Participant(
							resultSet.getLong("idparticipant"),
							resultSet.getLong("idevent"),
							resultSet.getLong("idusers")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return participant;		
	}
	



}
