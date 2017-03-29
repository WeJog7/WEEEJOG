package hei.devweb.wejog.entities;



public class Article {
	private Integer idarticle;
	private String nomarticle;
	private String contenuarticle;
	private String lien;
	private Long userCreatorId;
	private String creatorFirstName;
	
	
	public String getCreatorFirstName() {
		return creatorFirstName;
	}


	public void setCreatorFirstName(String creatorFirstName) {
		this.creatorFirstName = creatorFirstName;
	}


	public Article(Integer idarticle, String nomarticle,String contenuarticle,String lien,Long userCreatorId, String creatorFirstName) {
		super();
		this.idarticle=idarticle;
		this.nomarticle=nomarticle;
		this.contenuarticle=contenuarticle;
		this.lien=lien;
		this.userCreatorId=userCreatorId;
		this.creatorFirstName=creatorFirstName;
	}
	
	
	public Integer getIdarticle() {
		return idarticle;
	}
	public void setIdarticle(Integer idarticle) {
		this.idarticle = idarticle;
	}
	public String getNomarticle() {
		return nomarticle;
	}
	public void setNomarticle(String nomarticle) {
		this.nomarticle = nomarticle;
	}
	
	public String getContenuarticle() {
		return contenuarticle;
	}
	public void setContenuarticle(String contenuarticle) {
		this.contenuarticle = contenuarticle;
	}
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	public Long getUserCreatorId() {
		return userCreatorId;
	}
	public void setUserCreator(Long userId) {
		this.userCreatorId = userId;
	}
	
}