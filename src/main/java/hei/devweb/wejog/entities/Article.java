package hei.devweb.wejog.entities;



public class Article {
	private Integer idarticle;
	private String nomarticle;

	private String contenuarticle;
	private String lien;
	private Integer user;
	
	
	
	
	public Article(Integer idarticle, String nomarticle,String contenuarticle,String lien,Integer user) {
		super();
		this.idarticle=idarticle;
		this.nomarticle=nomarticle;
		
		this.contenuarticle=contenuarticle;
		this.lien=lien;
		this.user=user;
		
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
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	

}
