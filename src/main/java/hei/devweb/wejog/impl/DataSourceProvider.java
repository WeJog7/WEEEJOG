package hei.devweb.wejog.impl;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataSourceProvider {
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI jdbUri = new URI(System.getenv("mysql://lozc5foy2vx2n16n:gkdrho5mubz59i7h@nr84dudlpkazpylz.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/y2676mi8yyl5zwmr"));

	    String username = jdbUri.getUserInfo().split(":")[0];
	    String password = jdbUri.getUserInfo().split(":")[1];
	    String port = String.valueOf(jdbUri.getPort());
	    String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

	    return DriverManager.getConnection(jdbUrl, username, password);
	}

}
