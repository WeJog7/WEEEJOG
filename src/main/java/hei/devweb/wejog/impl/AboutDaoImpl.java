package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hei.devweb.wejog.exceptions.WejogSQLException;

public class AboutDaoImpl {
	
	public String getContenu() {
		String contenu = null;
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT description FROM about")){
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				contenu = resultSet.getString("description");
			}
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
		return contenu;
	}
	
	public void updateAbout(String aboutContenu){
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("UPDATE about SET description=?")){
			statement.setString(1, aboutContenu);
			statement.executeUpdate();
			statement.close();
			connection.close();
		}} catch (SQLException e) {
			throw new WejogSQLException(e);
		}
	}

}
