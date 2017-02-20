package hei.devweb.wejog.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Performance {
	private Integer idperformance;
	private LocalDate dateperformance;
	private LocalTime horaireperformance;
	private Double dureeperformance;
	private Double distanceperformance;
	private Double vitesseperformance;
	private Double calories;
	private String lieuperformance; 
	private  Integer user ;
	
	
	
	
	
	
	
	
	
	
	
	public Performance(Integer idperformance, LocalDate dateperformance, LocalTime horaireperformance, double dureeperformance, double distanceperformance,double vitesseperformance, double calories, String lieuperformance,  Integer user) {
		super();
		this.idperformance = idperformance;
		this.dateperformance = dateperformance;
		this.horaireperformance = horaireperformance;
		this.dureeperformance = dureeperformance;
		this.distanceperformance=distanceperformance;
		this.vitesseperformance=vitesseperformance;
		this.calories=calories;
		this.lieuperformance=lieuperformance;
		
		this.user=user;
		
		
	}
	
	public Integer getIdperformance() {
		return idperformance;
	}
	public void setIdperformance(Integer idperformance) {
		this.idperformance = idperformance;
	}
	public LocalDate getDateperformance() {
		return dateperformance;
	}
	public void setDateperformance(LocalDate dateperformance) {
		this.dateperformance = dateperformance;
	}
	public LocalTime getHoraireperformance() {
		return horaireperformance;
	}
	public void setHoraireperformance(LocalTime horaireperformance) {
		this.horaireperformance = horaireperformance;
	}
	public Double getDureeperformance() {
		return dureeperformance;
	}
	public void setDureeperformance(Double dureeperformance) {
		this.dureeperformance = dureeperformance;
	}
	public Double getDistanceperformance() {
		return distanceperformance;
	}
	public void setDistanceperformance(Double distanceperformance) {
		this.distanceperformance = distanceperformance;
	}
	public Double getVitesseperformance() {
		return vitesseperformance;
	}
	public void setVitesseperformance(Double vitesseperformance) {
		this.vitesseperformance = vitesseperformance;
	}
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	public String getLieuperformance() {
		return lieuperformance;
	}
	public void setLieuperformance(String lieuperformance) {
		this.lieuperformance = lieuperformance;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}

}


