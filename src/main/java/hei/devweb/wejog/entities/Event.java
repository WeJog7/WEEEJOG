package hei.devweb.wejog.entities;


import java.time.LocalDate;


public class Event {
	
	private Integer idevent;
	private LocalDate dateevent;
	private String horaireevent;
	private String momentOfTheDay;
	private Double dureeevent;
	private Double distanceevent;
	private String lieuevent; 
	private Long usergestion ;
	private String userGestionFirstName;


	public Event (Integer idevent, LocalDate dateevent, String horaireevent, String momentOfTheDay, double dureeevent, double distanceevent,String lieuevent, 
			Long userIdCreator, String userGestionFirstName) {
		super();
		this.idevent = idevent;
		this.dateevent = dateevent;
		this.horaireevent = horaireevent;
		this.momentOfTheDay = momentOfTheDay;
		this.dureeevent = dureeevent;
		this.distanceevent=distanceevent;
		this.lieuevent=lieuevent;
		this.usergestion=userIdCreator;
		this.userGestionFirstName= userGestionFirstName;
	}


	public String getMomentOfTheDay() {
		return momentOfTheDay;
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


	public String getHoraireevent() {
		return horaireevent;
	}


	public void setHoraireevent(String horaireevent) {
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


	public Long getUsergestion() {
		return usergestion;
	}


	public void setUsergestion(Long usergestion) {
		this.usergestion = usergestion;
	}


	public String getUserGestionFirstName() {
		return userGestionFirstName;
	}


	public void setUserGestionFirstName(String userGestionFirstName) {
		this.userGestionFirstName = userGestionFirstName;
	}

}