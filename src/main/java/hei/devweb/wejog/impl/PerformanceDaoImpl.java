package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class PerformanceDaoImpl {	


	public List<Performance> ListPerfomanceToDo(long idusers){
		List<Performance> performance = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM performance WHERE userCreatorId=? "
					+ "ORDER BY dateperformance DESC")){
				statement.setLong(1, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					performance.add(new Performance(
							resultSet.getLong("idperformance"),
							resultSet.getDate("dateperformance").toLocalDate(),
							resultSet.getDouble("dureeperformance"),
							resultSet.getDouble("distanceperformance"),
							resultSet.getDouble("vitesseperformance"),
							resultSet.getDouble("calories"),
							resultSet.getLong("userCreatorId")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return performance;		
	}



	public Performance addPerformance(Performance newPerformance){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `performance`(`dateperformance`,`dureeperformance`,"
					+ "`distanceperformance`,`vitesseperformance`,`calories`,userCreatorId)VALUES(?,?,?,?,?,?);", 
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1,Date.valueOf(newPerformance.getDateperformance()));
			statement.setDouble(2,newPerformance.getDureeperformance());
			statement.setDouble(3,newPerformance.getDistanceperformance());
			statement.setDouble(4,newPerformance.getVitesseperformance());
			statement.setDouble(5,newPerformance.getCalories());
			statement.setLong(6,newPerformance.getUserCreatorId());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newPerformance.setIdperformance(resultSet.getLong(1));

			}
			statement.close();
			connection.close();
		}

		catch (SQLException e){
			e.printStackTrace();

		}
		return newPerformance;
	}

	public void supprimerPerformance(long idperformance) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  performance WHERE idperformance=?")){
				statement.setLong(1, idperformance);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}
	
	
	private Performance mapperVersPerformance(ResultSet resultSet) throws SQLException {

		return new Performance (resultSet.getLong("idperformance"),
				resultSet.getDate("dateperformance").toLocalDate(),
				resultSet.getDouble("dureeperformance"),
				resultSet.getDouble("distanceperformance"),
				resultSet.getDouble("vitesseperformance"),
				resultSet.getDouble("calories"),
				resultSet.getLong("userCreatorId"));
	}

	
	public Performance getperformance(Long idperformance){
		Performance performance = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM performance WHERE idperformance=?")){
				statement.setLong(1, idperformance);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					performance = mapperVersPerformance(resultSet);
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return performance;		
	}
	
	public List<Performance> friendsPerformances(long idusers){
		List<Performance> performance = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, dateperformance, dureeperformance, distanceperformance,"
					+ "vitesseperformance, calories "
					+ "FROM performance "
					+ "LEFT JOIN ami AS A1 ON performance.userCreatorId=A1.idusers1 "
					+ "LEFT JOIN ami AS A2 ON performance.userCreatorId=A2.idusers2 "
					+ "INNER JOIN users ON performance.userCreatorId=users.idusers "
					+ "WHERE A1.idusers2=? OR A2.idusers1=? "
					+ "ORDER BY dateperformance DESC")){
				statement.setLong(1, idusers);
				statement.setLong(2, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					performance.add(new Performance(
							resultSet.getString("prenom"),
							resultSet.getDate("dateperformance").toLocalDate(),
							resultSet.getDouble("dureeperformance"),
							resultSet.getDouble("distanceperformance"),
							resultSet.getDouble("vitesseperformance"),
							resultSet.getDouble("calories")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return performance;	
	}

}