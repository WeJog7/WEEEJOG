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

	private Performance mapperVersPerformance(ResultSet resultSet) throws SQLException {

		return new Performance (resultSet.getLong("idPerformance"),
				resultSet.getDate("date").toLocalDate(),
				resultSet.getDouble("duration"),
				resultSet.getDouble("distance"),
				resultSet.getDouble("speed"),
				resultSet.getDouble("calories"),
				resultSet.getLong("creatorId"));
	}


	public List<Performance> ListPerfomanceToDo(long idusers){
		List<Performance> performance = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM performance WHERE creatorId=? "
					+ "ORDER BY date DESC")){
				statement.setLong(1, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					performance.add(mapperVersPerformance(resultSet));
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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `performance`(`date`,`duration`,"
					+ "`distance`,`speed`,`calories`,creatorId)VALUES(?,?,?,?,?,?);", 
					Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1,Date.valueOf(newPerformance.getDate()));
			statement.setDouble(2,newPerformance.getDuration());
			statement.setDouble(3,newPerformance.getDistance());
			statement.setDouble(4,newPerformance.getSpeed());
			statement.setDouble(5,newPerformance.getCalories());
			statement.setLong(6,newPerformance.getCreatorId());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newPerformance.setIdPerformance(resultSet.getLong(1));

			}
			statement.close();
			connection.close();
		}

		catch (SQLException e){
			e.printStackTrace();

		}
		return newPerformance;
	}

	public void supprimerPerformance(long idPerformance) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  performance WHERE idPerformance=?")){
				statement.setLong(1, idPerformance);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}


	public Performance getperformance(Long idPerformance){
		Performance performance = null ;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM performance WHERE idPerformance=?")){
				statement.setLong(1, idPerformance);
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
			try(PreparedStatement statement = connection.prepareStatement("SELECT prenom, picturePath, date, duration, "
					+ "distance, speed, calories "
					+ "FROM performance "
					+ "LEFT JOIN ami AS A1 ON performance.creatorId=A1.idusers1 "
					+ "LEFT JOIN ami AS A2 ON performance.creatorId=A2.idusers2 "
					+ "INNER JOIN users ON performance.creatorId=users.idusers "
					+ "WHERE A1.idusers2=? OR A2.idusers1=? "
					+ "ORDER BY date DESC")){
				statement.setLong(1, idusers);
				statement.setLong(2, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					performance.add(new Performance(resultSet.getString("prenom"),
							resultSet.getString("picturePath"),
							resultSet.getDate("date").toLocalDate(),
							resultSet.getDouble("duration"),
							resultSet.getDouble("distance"),
							resultSet.getDouble("speed"),
							resultSet.getDouble("calories")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return performance;	
	}

	public int countTimePerformance(long creatorId){
		int count = 0;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("(SELECT SUM(duration)  AS total FROM performance "
					+ "WHERE creatorId=?)")){
				statement.setLong(1, creatorId);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					count = resultSet.getInt("total");
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return count;	

	}
	public int countDistance(long creatorId){
		int count = 0;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("(SELECT SUM(distance)  AS total FROM performance "
					+ "WHERE creatorId=?)")){
				statement.setLong(1, creatorId);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					count = resultSet.getInt("total");
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return count;	

	}
	public int countNumberOfRace(long creatorId){
		int count = 0;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("(SELECT COUNT(idPerformance)  AS total FROM performance "
					+ "WHERE creatorId=?)")){
				statement.setLong(1, creatorId);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					count = resultSet.getInt("total");
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return count;	

	}

}