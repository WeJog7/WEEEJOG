package hei.devweb.wejog.daos;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.wejog.impl.DataSourceProvider;
import hei.devweb.wejog.impl.UserDaoImpl;
import hei.devweb.wejog.entities.User;

public class UserDaoTestCase {
	private UserDaoImpl userdao= new UserDaoImpl();
@Before
public void initDatabase() throws Exception{
	try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
	   Statement statement = connection.createStatement()){
		statement.executeUpdate("DELETE FROM users");
		statement.executeUpdate("INSERT INTO users(idusers, nom, prenom,datedenaissance, mail,sexe,motdepasse, admin ) VALUES ( 10, 'benallal','habib','2016-11-20', 'test@test.fr','1','0000','0')");
		
	   }
	   }
@Test
public void shouldListUserToDo() throws Exception {
	
	List<User> user = userdao.listerUsers();
	Assertions.assertThat(user).hasSize(1);
	Assertions.assertThat(user).extracting("idusers"," nom", "prenom","datedenaissance", "mail","sexe","motdepasse", "admin" ).containsOnly(
	Assertions.tuple(1, "benallal","habib","2016-11-20", "test@test.fr","1","0000","0")
	
	
	);
	
}
}
