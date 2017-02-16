package hei.devweb.wejog.entities;


import java.time.LocalDate;


public class Event {
	private Integer idevent;
	private LocalDate dateevent;
	private LocalDate horaireevent;
	private Double dureeevent;
	private Double distanceevent;
	private String lieuevent; 
	private  Integer usergestion ;
	private Integer userparticipant;
	
	
	public Event (int idevent, LocalDate dateevent, LocalDate horaireevent, double dureeevent, double distanceevent,String lieuevent, int usergestion, int userparticipant) {
		super();
		this.idevent = idevent;
		this.dateevent = dateevent;
		this.horaireevent = horaireevent;
		this.dureeevent = dureeevent;
		this.distanceevent=distanceevent;
		this.lieuevent=lieuevent;
		this.usergestion=usergestion;
		this.userparticipant=userparticipant;
		
	}


	

	public Integer getIdevent() {
		return idevent;
	}


	public void setIdevent(Integer idevent) {
		this.idevent = idevent;
	}


	public LocalDate getDateevent() {
		return dateevent;
	}


	public void setDateevent(LocalDate dateevent) {
		this.dateevent = dateevent;
	}


	public LocalDate getHoraireevent() {
		return horaireevent;
	}


	public void setHoraireevent(LocalDate horaireevent) {
		this.horaireevent = horaireevent;
	}


	public Double getDureeevent() {
		return dureeevent;
	}


	public void setDureeevent(Double dureeevent) {
		this.dureeevent = dureeevent;
	}


	public Double getDistanceevent() {
		return distanceevent;
	}


	public void setDistanceevent(Double distanceevent) {
		this.distanceevent = distanceevent;
	}


	public String getLieuevent() {
		return lieuevent;
	}


	public void setLieuevent(String lieuevent) {
		this.lieuevent = lieuevent;
	}


	public Integer getUsergestion() {
		return usergestion;
	}


	public void setUsergestion(Integer usergestion) {
		this.usergestion = usergestion;
	}


	public Integer getUserparticipant() {
		return userparticipant;
	}


	public void setUserparticipant(Integer userparticipant) {
		this.userparticipant = userparticipant;
	} 
	
	
	

}
