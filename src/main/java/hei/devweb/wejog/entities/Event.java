package hei.devweb.wejog.entities;

import java.sql.Time;
import java.time.LocalDate;

public class Event {
	private Integer idEvent;
	private LocalDate DateEvent;
	private Time HoraireEvent;
	private Double DureeEvent;
	private Double DistanceEvent;
	private String LieuEvent; 
	private  Integer UserGestion ;
	private Integer UserParticipant;
	
	
	public Event (Integer idEvent, LocalDate DateEvent, Time HoraireEvent, double DureeEvent, Double DistanceEvent,String LieuEvent, Integer UserGestion, Integer UserParticipant) {
		super();
		this.idEvent = idEvent;
		this.DateEvent = DateEvent;
		this.HoraireEvent = HoraireEvent;
		this.DureeEvent = DureeEvent;
		this.DistanceEvent=DistanceEvent;
		this.LieuEvent=LieuEvent;
		this.UserGestion=UserGestion;
		this.UserParticipant=UserParticipant;
		
	} 
	
	
	public Integer getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	public LocalDate getDateEvent() {
		return DateEvent;
	}
	public void setDateEvent(LocalDate dateEvent) {
		DateEvent = dateEvent;
	}
	public Time getHoraireEvent() {
		return HoraireEvent;
	}
	public void setHoraireEvent(Time horaireEvent) {
		HoraireEvent = horaireEvent;
	}
	public Double getDureeEvent() {
		return DureeEvent;
	}
	public void setDureeEvent(Double dureeEvent) {
		DureeEvent = dureeEvent;
	}
	public Double getDistanceEvent() {
		return DistanceEvent;
	}
	public void setDistanceEvent(Double distanceEvent) {
		DistanceEvent = distanceEvent;
	}
	public String getLieuEvent() {
		return LieuEvent;
	}
	public void setLieuEvent(String lieuEvent) {
		LieuEvent = lieuEvent;
	}
	public Integer getUserGestion() {
		return UserGestion;
	}
	public void setUserGestion(Integer userGestion) {
		UserGestion = userGestion;
	}
	public Integer getUserParticipant() {
		return UserParticipant;
	}
	public void setUserParticipant(Integer userParticipant) {
		UserParticipant = userParticipant;
	}
	
	
	

}
