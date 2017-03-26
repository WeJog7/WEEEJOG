package hei.devweb.wejog.entities;

import java.time.LocalDate;

public class Performance {
	private Integer idperformance;
	private LocalDate dateperformance;
	private Double dureeperformance;
	private Double distanceperformance;
	private Double vitesseperformance;
	private Double calories;
	private Long userCreatorId ;
	
	public Performance(Integer idperformance, LocalDate dateperformance, double dureeperformance, double distanceperformance,
			double vitesseperformance, double calories, long userCreatorId) {
		super();
		this.idperformance = idperformance;
		this.dateperformance = dateperformance;
		this.dureeperformance = dureeperformance;
		this.distanceperformance=distanceperformance;
		this.vitesseperformance=vitesseperformance;
		this.calories=calories;
		this.userCreatorId=userCreatorId;
		
		
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
	public Long getUserCreatorId() {
		return userCreatorId;
	}
	public void setUser(Long userId) {
		this.userCreatorId = userId;
	}

}