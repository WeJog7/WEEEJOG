package hei.devweb.wejog.entities;

import java.io.Serializable;
import java.time.LocalDate;


public class User implements Serializable {
	private static final long serialVersionUID = -1167387777176439635L;

	private Long idusers;
	private String nom;
	private String prenom;
	private String mail;
	private LocalDate datedenaissance ;
	private String motdepasse;
	private boolean sexe ;
	private boolean admin;


	public User(String nom, String prenom, String mail, LocalDate datedenaissance,String motdepasse,boolean sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.datedenaissance = datedenaissance;
		this.motdepasse= motdepasse;
		this.sexe=sexe;
	}
	
	public User(Long idusers, String nom, String prenom, String mail, LocalDate datedenaissance,boolean sexe, boolean admin) {
		super();
		this.idusers = idusers;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.datedenaissance = datedenaissance;
		this.sexe=sexe;
		this.admin = admin;
	}
	
	
	public Long getIdusers() {
		return idusers;
	}
	public void setIdusers(Long idusers) {
		this.idusers = idusers;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public LocalDate getDatedenaissance() {
		return datedenaissance;
	}
	public void setDatedenaissance(LocalDate datedenaissance) {
		this.datedenaissance = datedenaissance;
	}
	public boolean isSexe() {
		return sexe;
	}
	public void setSexe(boolean sexe) {
		this.sexe = sexe;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


}
