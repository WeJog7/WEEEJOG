package hei.devweb.wejog.entities;

import java.time.LocalDate;

public class Performance {
	private Long idPerformance;
	private LocalDate date;
	private Double duration;
	private Double distance;
	private Double speed;
	private Double calories;
	private Long creatorId ;
	private String prenom;
	private String picturePath;


	public Performance(Long idPerformance, LocalDate date, double duration, double distance,
			double speed, double calories, long creatorId) {
		this.idPerformance = idPerformance;
		this.date = date;
		this.duration = duration;
		this.distance=distance;
		this.speed=speed;
		this.calories=calories;
		this.creatorId=creatorId;
	}
	
	public Performance(String prenom, String picturePath, LocalDate date, double duration, double distance,
			double speed, double calories, long creatorId) {
		this.prenom=prenom;
		this.picturePath=picturePath;
		this.date = date;
		this.duration = duration;
		this.distance=distance;
		this.speed=speed;
		this.calories=calories;
		this.creatorId=creatorId;
	}
	
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public Long getIdPerformance() {
		return idPerformance;
	}
	public void setIdPerformance(Long idPerformance) {
		this.idPerformance = idPerformance;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId (Long userId) {
		this.creatorId = userId;
	}

}