package hei.devweb.wejog.managers;


import java.util.List;
import hei.devweb.wejog.entities.Event;
import hei.devweb.wejog.impl.EventDaoImpl;


public class EventService {
	private EventDaoImpl eventDao = new EventDaoImpl();
	
	private static class JoueurServiceHolder {
		private static EventService instance = new EventService();
	}

	public static EventService getInstance() {
		return JoueurServiceHolder.instance;
	}

	private EventService() {
	}

	public  List<Event> ListEventToDo(){
		return eventDao.ListEventToDo();
		
	}

	
	public void AddEvent(Event newEvent) {
		// TODO Auto-generated method stub
		
	} 

}