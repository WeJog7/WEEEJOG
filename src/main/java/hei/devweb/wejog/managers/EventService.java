package hei.devweb.wejog.managers;


import java.sql.Date;
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

	public  List<Event> ListEventToDo(Date todayDate, long idusers){
		return eventDao.ListEventToDo(todayDate, idusers);
	}

	public Event getEvent(Long idevent){
		return eventDao.getEvent(idevent);
	}

	public  List<Event> ListmyEventAdministrated(long idusers, Date todayDate){
		return eventDao.ListmyEventAdministrated(idusers, todayDate);
	}

	public List<Event> listEventsSubscribed(long idusers, Date todayDate){
		return eventDao.listEventsSubscribed(idusers, todayDate);
	}

	public void addEvent(Event newEvent) {
		eventDao.addEvent(newEvent);
	} 
	
	public void deleteEvent(Long idEvent) {
		eventDao.deleteEvent(idEvent);
	}

}
