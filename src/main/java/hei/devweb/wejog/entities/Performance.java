package hei.devweb.wejog.entities;

import java.time.LocalDate;

public class Performance {
	private Long idperformance;
	private LocalDate dateperformance;
	private Double dureeperformance;
	private Double distanceperformance;
	private Double vitesseperformance;
	private Double calories;
	private Long userCreatorId ;
	private String prenom;
	private String picturePath;
	
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Performance(Long idperformance, LocalDate dateperformance, double dureeperformance, double distanceperformance,
			double vitesseperformance, double calories, long userCreatorId) {
		this.idperformance = idperformance;
		this.dateperformance = dateperformance;
		this.dureeperformance = dureeperformance;
		this.distanceperformance=distanceperformance;
		this.vitesseperformance=vitesseperformance;
		this.calories=calories;
		this.userCreatorId=userCreatorId;
	}
	
	public Performance(String prenom, String picturePath, LocalDate dateperformance, double dureeperformance, double distanceperformance,
			double vitesseperformance, double calories) {
		this.prenom=prenom;
		this.picturePath=picturePath;
		this.dateperformance = dateperformance;
		this.dureeperformance = dureeperformance;
		this.distanceperformance=distanceperformance;
		this.vitesseperformance=vitesseperformance;
		this.calories=calories;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public Long getIdperformance() {
		return idperformance;
	}
	public void setIdperformance(Long idperformance) {
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