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
			dataSource.setServerName("localhost4");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("ay3bszwzcjnh8o4f");
			dataSource.setUser("root");
			dataSource.setPassword("root");
		}
		return dataSource;
	}

	

}
