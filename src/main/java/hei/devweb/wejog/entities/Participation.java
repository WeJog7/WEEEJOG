package hei.devweb.wejog.entities;

public class Participation {

	private Integer idparticipant;
	private Integer idevent;
	private Integer idusers;
	
	public Participation(Integer idparticipant,Integer idevent,Integer idusers ) {
		super();
		this.idparticipant=idparticipant;
		this.idevent=idevent;
		this.idusers=idusers;
	}

	public Integer getIdparticipant() {
		return idparticipant;
	}

	public void setIdparticipant(Integer idparticipant) {
		this.idparticipant = idparticipant;
	}

	public Integer getIdevent() {
		return idevent;
	}

	public void setIdevent(Integer idevent) {
		this.idevent = idevent;
	}

	public Integer getIdusers() {
		return idusers;
	}

	public void setIdusers(Integer idusers) {
		this.idusers = idusers;
	}
	
}
