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
		List<User> user = new ArrayList<User>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery("SELECT idusers, nom, prenom, datedenaissance, mail, sexe, "
						+ "admin, description, picturePath FROM users ORDER BY mail")){
			while (resultSet.next()) {
<<<<<<< HEAD

				user.add(mapperVersUser(resultSet));
=======
				users.add(mapperVersUser(resultSet));
>>>>>>> branch 'master' of https://github.com/WeJog7/WEEEJOG.git
			}
			statement.close();
			connection.close();
		}}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
		return user;
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
			try(PreparedStatement statement = connection.prepareStatement("SELECT idusers, nom, prenom,datedenaisse, mail, sexe,"
					+ " admin, description, picturePath FROM admin FROM users WHERE idusers=?")){
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

	
	public void modifiermotdepasse(long idusers, String motdepasse) {
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
	

	@Override
	public User getUser(Long idusers) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void modifierMotDePasse(Long id, String motDePasse) {
		// TODO Auto-generated method stub
		
	}

}