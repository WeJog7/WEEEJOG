package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.exceptions.WejogSQLException;

public class CoupleAmiDaoImpl {

	public List<CoupleAmis> ListAmis(long idusers){
		List<CoupleAmis> coupleamis = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM ami WHERE idusers1=? OR idusers2=? ")){
				statement.setLong(1, idusers);
				statement.setLong(2, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					coupleamis.add(new CoupleAmis(
							resultSet.getLong("idami"),
							resultSet.getLong("idusers1"),
							resultSet.getLong("idusers2")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return coupleamis;		
	}


	public void deleteFriend(long idusers1, long idusers2) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement(" DELETE FROM ami WHERE "
					+ "(idusers1=? AND idusers2=?) OR (idusers1=? AND idusers2=?)")){
				statement.setLong(1, idusers1);
				statement.setLong(2, idusers2);
				statement.setLong(3, idusers2);
				statement.setLong(4, idusers1);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}
		}catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}


	public CoupleAmis sendInvitationToBeFriend(CoupleAmis newAmis){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `askfriend`(`idusers1`, `idusers2`)VALUES(?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1,newAmis.getIdusers1());
			statement.setLong(2,newAmis.getIdusers2());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newAmis.setIdami(resultSet.getLong(1));
			}
			statement.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return newAmis;
	}


	public void deleteInvitation(long idusers1, long idusers2) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  askfriend "
					+ "WHERE (idusers1=? AND idusers2=?) OR (idusers1=? AND idusers2=?)")){
				statement.setLong(1, idusers1);
				statement.setLong(2, idusers2);
				statement.setLong(3, idusers2);
				statement.setLong(4, idusers1);
				statement.executeUpdate();
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
	}


	public CoupleAmis acceptFiend(CoupleAmis newAmis){
		try {
			Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO `ami`(`idusers1`, `idusers2`)VALUES(?,?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1,newAmis.getIdusers1());
			statement.setLong(2,newAmis.getIdusers2());
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				newAmis.setIdami(resultSet.getLong(1));
			}
			statement.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return newAmis;
	}


	public List<CoupleAmis> ListAsking(long idusers2){
		List<CoupleAmis> coupleamis = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("(SELECT * FROM askfriend WHERE idusers2=?)")){
				statement.setLong(1, idusers2);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					coupleamis.add(new CoupleAmis(
							resultSet.getLong("idaskfriend"),
							resultSet.getLong("idusers1"),
							resultSet.getLong("idusers2")));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return coupleamis;		
	}


	public int countAsking(long idusers2){
		int count = 0;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT COUNT(idaskfriend) AS total FROM askfriend WHERE idusers2=?")){
				statement.setLong(1, idusers2);
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

	
	public CoupleAmis getFriendCouple(long idusers, long idFriend){
		CoupleAmis coupleamis = null;		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM ami "
					+ "WHERE (idusers1=? AND idusers2=?) OR (idusers1=? AND idusers2=?)")){
				statement.setLong(1, idusers);
				statement.setLong(2, idFriend);
				statement.setLong(3, idFriend);
				statement.setLong(4, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					coupleamis = new CoupleAmis(
							resultSet.getLong("idami"),
							resultSet.getLong("idusers1"),
							resultSet.getLong("idusers2"));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return coupleamis;		
	}
	
	
	public CoupleAmis getInvitation(long idusers, long idFriend){
		CoupleAmis coupleamis = null;		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM askfriend "
					+ "WHERE idusers1=? AND idusers2=?")){
				statement.setLong(1, idusers);
				statement.setLong(2, idFriend);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					coupleamis = new CoupleAmis(
							resultSet.getLong("idaskfriend"),
							resultSet.getLong("idusers1"),
							resultSet.getLong("idusers2"));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return coupleamis;		
	}
	

}
