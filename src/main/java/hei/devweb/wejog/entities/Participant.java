package hei.devweb.wejog.entities;

public class Participant {

	private Long idColonneParticipant;
	private Long idevent;
	private Long idusers;
	
	public Participant(Long idColonneParticipant,Long idevent,Long idusers) {
		super();
		this.idColonneParticipant=idColonneParticipant;
		this.idevent=idevent;
		this.idusers=idusers;
		
		
	}

	public Long getIdEventInscrit() {
		return idColonneParticipant;
	}

	public void setIdEventInscrit(Long idColonneParticipant) {
		this.idColonneParticipant = idColonneParticipant;
	}

	public Long getIdevent() {
		return idevent;
	}

	public void setIdevent(Long idevent) {
		this.idevent = idevent;
	}

	public Long getIdusers() {
		return idusers;
	}

	public void setIdusers(Long idusers) {
		this.idusers = idusers;
	}
	
}
