package hei.devweb.wejog.entities;


import java.time.LocalDate;


public class Event {
	
	private Long idEvent;
	private LocalDate date;
	private Integer hour;
	private Integer minutes;
	private String timeAsString;
	private String momentOfTheDay;
	private Double duration;
	private Double distance;
	private String place; 
	private Long creatorId ;
	private String managerFirstName;
	private String managerPicturePath;

	/** public Event
	 * Constructor made for creating an event in database
	 */
	public Event (Long idEvent, LocalDate date, String timeAsString, Integer hour, Integer minutes, String momentOfTheDay, 
			double duration, double distance,String place, Long creatorId) {
		this.idEvent = idEvent;
		this.date = date;
		this.hour = hour;
		this.minutes = minutes;
		this.timeAsString=timeAsString;
		this.momentOfTheDay = momentOfTheDay;
		this.duration = duration;
		this.distance=distance;
		this.place=place;
		this.creatorId=creatorId;
	}

	/**
	 * Constructor made for creating a list of events
	 */
	public Event (Long idEvent, LocalDate date, String timeAsString, String momentOfTheDay, double duration, double distance,
			String place, String managerFirstName, String managerPicturePath) {
		this.idEvent = idEvent;
		this.date = date;
		this.timeAsString=timeAsString;
		this.momentOfTheDay = momentOfTheDay;
		this.duration = duration;
		this.distance=distance;
		this.place=place;
		this.managerFirstName= managerFirstName;
		this.managerPicturePath=managerPicturePath;
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


	public Long getIdEvent() {
		return idEvent;
	}


	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public String getTimeAsString() {
		return timeAsString;
	}

	
	public void setTimeAsString(String timeAsString) {
		this.timeAsString = timeAsString;
	}


	public Integer getHour() {
		return hour;
	}


	public void setHour(Integer hour) {
		this.hour = hour;
	}


	public Double getDuration() {
		return duration;
	}


	public void setDuration(Double duration) {
		this.duration = duration;
	}


	public Double getDistance() {
		return distance;
	}


	public void setDistance(Double distance) {
		this.distance = distance;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public Long getCreatorId() {
		return creatorId;
	}

	
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	
	public String getManagerFirstName() {
		return managerFirstName;
	}


	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	
	public String getManagerPicturePath() {
		return managerPicturePath;
	}

	
	public void setManagerPicturePath(String managerPicturePath) {
		this.managerPicturePath = managerPicturePath;
	}
}