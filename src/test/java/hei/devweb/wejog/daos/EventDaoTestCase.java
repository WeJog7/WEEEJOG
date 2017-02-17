package hei.devweb.wejog.daos;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.impl.DataSourceProvider;

import hei.devweb.wejog.impl.EventDaoImpl;


public class EventDaoTestCase {
	
	private EventDaoImpl videodao= new EventDaoImpl();
	@Before
	public void initDatabase() throws Exception{
		try ( Connection connection = DataSourceProvider.getDataSource().getConnection();
		   Statement statement = connection.createStatement()){
			statement.executeUpdate("DELETE FROM event");
			statement.executeUpdate("INSERT INTO event(idevent,dateevent, horaireevent, dureeevent,distanceevent,lieuevent,user1,user2 ) VALUES ( 1, '2016-11-20','12:12:00','20.0','5.0','billy', '1','2')");
			
		   }
		   }
/*	@Test
	public void shouldListEventToDo() throws Exception {
		
		List<Event> video = videodao.ListEventToDo();
		Assertions.assertThat(video).hasSize(1);
		Assertions.assertThat(video).extracting("idvideo", "nom","date","duree","idjoueur").containsOnly(
		Assertions.tuple(1,"billyPSG",LocalDate.of(2016, 11,20),20.0)
		
		);
		
	}*/

}
