package hei.devweb.wejog.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
