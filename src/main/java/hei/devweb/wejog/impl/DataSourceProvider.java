package hei.devweb.wejog.impl;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DataSourceProvider {
	
	private static MysqlDataSource dataSource;

	public static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("y2676mi8yyl5zwmr");
			dataSource.setUser("lozc5foy2vx2n16n");
			dataSource.setPassword("gkdrho5mubz59i7h");
		}
		return dataSource;
	}

	

}
