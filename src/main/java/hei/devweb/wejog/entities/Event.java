package hei.devweb.wejog.entities;


import java.time.LocalDate;


public class Event {
	
	private Long idevent;
	private LocalDate dateevent;
	private Integer hour;
	private Integer minutes;
	private String timeAsString;
	private String momentOfTheDay;
	private Double dureeevent;
	private Double distanceevent;
	private String lieuevent; 
	private Long usergestion ;
	private String userGestionFirstName;


	public Event (Long idevent, LocalDate dateevent, String timeAsString, Integer hour, Integer minutes, String momentOfTheDay, 
			double dureeevent, double distanceevent,String lieuevent, Long userIdCreator, String userGestionFirstName) {
		this.idevent = idevent;
		this.dateevent = dateevent;
		this.hour = hour;
		this.minutes = minutes;
		this.timeAsString=timeAsString;
		this.momentOfTheDay = momentOfTheDay;
		this.dureeevent = dureeevent;
		this.distanceevent=distanceevent;
		this.lieuevent=lieuevent;
		this.usergestion=userIdCreator;
		this.userGestionFirstName= userGestionFirstName;
	}
	
	public String getTimeAsString() {
		return timeAsString;
	}

	public void setTimeAsString(String timeAsString) {
		this.timeAsString = timeAsString;
	}

	public Event (Long idevent, LocalDate dateevent, String timeAsString, String momentOfTheDay, double dureeevent, double distanceevent,
			String lieuevent, Long userIdCreator, String userGestionFirstName) {
		this.idevent = idevent;
		this.dateevent = dateevent;
		this.timeAsString=timeAsString;
		this.momentOfTheDay = momentOfTheDay;
		this.dureeevent = dureeevent;
		this.distanceevent=distanceevent;
		this.lieuevent=lieuevent;
		this.usergestion=userIdCreator;
		this.userGestionFirstName= userGestionFirstName;
	}


	public Integer getMinutes() {
		return minutes;
	}


	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}


	public String getMomentOfTheDay() {
		return momentOfTheDay;
	}


	public Long getIdevent() {
		return idevent;
	}


	public void setIdevent(Long idevent) {
		this.idevent = idevent;
	}


	public LocalDate getDateevent() {
		return dateevent;
	}


	public void setDateevent(LocalDate dateevent) {
		this.dateevent = dateevent;
	}


	public Integer getHour() {
		return hour;
	}


	public void setHour(Integer hour) {
		this.hour = hour;
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