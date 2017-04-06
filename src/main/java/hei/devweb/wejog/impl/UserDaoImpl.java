package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.exceptions.WejogSQLException;



public class UserDaoImpl implements Userdao {
	
	public List<User> listerUsers() {
		List<User> users = new ArrayList<User>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT idusers, nom, prenom, datedenaissance, mail, sexe, "
						+ "admin, description, picturePath FROM users WHERE admin=false ORDER BY mail ")){
			while (resultSet.next()) {
				users.add(mapperVersUser(resultSet));
			}
			statement.close();
			connection.close();
		}}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
		return users;
	}
	
	private User mapperVersUser(ResultSet resultSet) throws SQLException {
		
		return new User (resultSet.getLong("idusers"),
				resultSet.getString("nom"),
				resultSet.getString("prenom"),
				resultSet.getString("mail"),
				resultSet.getDate("datedenaissance").toLocalDate(),
				resultSet.getBoolean("sexe"),
                resultSet.getBoolean("admin"),
                resultSet.getString("description"),
				resultSet.getString("picturePath"));
	}
	

	public User getUser(long idusers) {
		User user = null;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idusers, nom, prenom,datedenaissance, mail, sexe,"
					+ " admin, description, picturePath FROM users WHERE idusers=?")){
		    statement.setLong(1, idusers);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = mapperVersUser(resultSet);
			}
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
		return user;
	}

	public User getUser(String mail) {
		User user = null;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT idusers, nom, prenom,datedenaissance, mail, sexe,"
					+ "admin, description, picturePath FROM users WHERE mail=?")){
			 statement.setString(1, mail);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = mapperVersUser(resultSet);
			}
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}

		return user;
	}
	
	
	public String getmotdepasse(String mail) {
		String motdepasse = null;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT motdepasse FROM users WHERE mail=?")){
		statement.setString(1, mail);
		ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				motdepasse = resultSet.getString("motdepasse");
			}
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}

		return motdepasse;
	}

	
	public void modifierMotDePasse(long idusers, String motdepasse) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE users SET motdepasse=? WHERE idusers=?")){
			statement.setString(1, motdepasse);
			statement.setLong(2, idusers);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}
	
	@Override
	public void supprimerusers(long idusers) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("DELETE  FROM  users WHERE idusers=?")){
			statement.setLong(1, idusers);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}


	@Override
	public User addUser(User newuser) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO users(nom, prenom,datedenaissance, mail, sexe, motdepasse) VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS)){
				{
				statement.setString(1, newuser.getNom());
				statement.setString(2, newuser.getPrenom());
				statement.setDate(3,Date.valueOf(newuser.getDatedenaissance()));
				statement.setString(4, newuser.getMail());
				statement.setBoolean(5, newuser.isSexe());
				statement.setString(6, newuser.getMotdepasse());
				statement.executeUpdate();
				try (ResultSet idusers = statement.getGeneratedKeys()) {
					if (idusers.next()) {
						newuser.setIdusers(idusers.getLong(1));
					}
				}
			}

		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		
	}
		return newuser;
	}
	
	
	public void modificationDescription(long idusers, String description){
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE users SET description=? WHERE idusers=?")){
			statement.setString(1, description);
			statement.setLong(2, idusers);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}
	
	
	public String getDescription(long idusers) {
		String description = null;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT description FROM users WHERE idusers=?")){
		statement.setLong(1, idusers);
		ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				description = resultSet.getString("description");
			}
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}

		return description;
	}
	
	
	public String generateRandomPassword() {
		int length = 7;
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
	}
	
	
	public List<User> ListSearchAmi(String identity, Long idusers){
		
		List<User> listSearch = new ArrayList<>();		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE (nom LIKE ?  OR prenom LIKE ?) AND idusers!=?")){
				statement.setString(1, "%"+identity+"%");
				statement.setString(2, "%"+identity+"%");
				statement.setLong(3, idusers);
				ResultSet resultSet = statement.executeQuery();
				while ( resultSet.next()){
					listSearch.add(mapperVersUser(resultSet));
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new WejogSQLException(e);
			}
		return listSearch;
		
	}
	
}