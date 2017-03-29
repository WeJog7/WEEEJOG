package hei.devweb.wejog.managers;


import java.util.List;
import hei.devweb.wejog.entities.Participant;
import hei.devweb.wejog.impl.ParticipantDaoImpl;


public class ParticipantService {
	private ParticipantDaoImpl participantDao = new ParticipantDaoImpl();
	
	private static class ParticipantServiceHolder {
		private static ParticipantService instance = new ParticipantService();
	}

	public static ParticipantService getInstance() {
		return ParticipantServiceHolder.instance;
	}
	
	public  List<Participant> ListEvenementsInscrits(long idusers){
		return participantDao.ListEvenementsInscrits(idusers);
		
	}
	
	public Participant RegistredToEvent(Participant newparticipant ){
		return participantDao.RegistredToEvent(newparticipant);
		
	}
	public void DesinscrireEvent(long idevent, long idusers){
		participantDao.DesinscrireEvent(idevent, idusers);
	}
}
