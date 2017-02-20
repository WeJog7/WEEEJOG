package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


import hei.devweb.wejog.entities.Performance;

public class PerformanceDaoImpl {	
	
	
	public List<Performance> ListPerfomanceToDo(){
	List<Performance> performance = new ArrayList<>();
	try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery("SELECT * FROM performance ")){
		while ( resultSet.next()){
			performance.add(new Performance(
					resultSet.getInt("idperformance"),
					resultSet.getDate("dateperformance").toLocalDate(),
					resultSet.getTime("horaireperformance").toLocalTime(),
					resultSet.getDouble("dureeperformance"),
					resultSet.getDouble("distanceperformance"),
					resultSet.getDouble("vitesseperformance"),
					resultSet.getDouble("calories"),
					resultSet.getString("lieuperformance"),
					resultSet.getInt("user")) );
		}
		statement.close();
		connection.close();
	}}}
	catch (SQLException e){
		e.printStackTrace();
	
	}
			

			return performance;
		}
	


public Performance addPerformance(Performance newPerformance){
	try {
		Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO `performance`(`dateperformance`,`horaireperformance`,`dureeperformance`,`distanceperformance`,`vitesseperformance`,`calories`,`lieuperformance`)VALUES(?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
	statement.setDate(1,Date.valueOf(newPerformance.getDateperformance()));
	statement.setTime(2,Time.valueOf(newPerformance.getHoraireperformance()));
	statement.setDouble(3,newPerformance.getDureeperformance());
    statement.setDouble(4,newPerformance.getDistanceperformance());
    statement.setDouble(3,newPerformance.getVitesseperformance());
    statement.setDouble(4,newPerformance.getCalories());
    statement.setString(5,newPerformance.getLieuperformance());
	
	
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newPerformance.setIdperformance(resultSet.getInt(1));
				
			}
			statement.close();
			connection.close();
		}
		
catch (SQLException e){
e.printStackTrace();

}
return newPerformance;
}




}
