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
	private String description;
	private String picturePath;
	private boolean block;
	private boolean deleted;
	private String activationKey;
	private Long idAccountNotActivated;
	
	
	public User(Long idAccountNotActivated, String nom, String prenom, String mail, LocalDate datedenaissance, String motdepasse, boolean sexe, String activationKey) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.datedenaissance = datedenaissance;
		this.motdepasse= motdepasse;
		this.sexe=sexe;
		this.activationKey=activationKey;
		this.idAccountNotActivated=idAccountNotActivated;
	}


	public User(String nom, String prenom, String mail, LocalDate datedenaissance, String motdepasse, boolean sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.datedenaissance = datedenaissance;
		this.motdepasse= motdepasse;
		this.sexe=sexe;
	}
	
	public User(Long idusers, String nom, String prenom, String mail, LocalDate datedenaissance, boolean sexe, boolean admin, String description,
			String picturePath, boolean block, boolean deleted) {
		super();
		this.idusers = idusers;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.datedenaissance = datedenaissance;
		this.sexe=sexe;
		this.admin = admin;
		this.description = description;
		this.picturePath = picturePath;
		this.block=block;
		this.deleted=deleted;
	}
	
	
	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public Long getIdAccountNotActivated() {
		return idAccountNotActivated;
	}


	public void setIdAccountNotActivated(Long idAccountNotActivated) {
		this.idAccountNotActivated = idAccountNotActivated;
	}
	
	
	public String getActivationKey() {
		return activationKey;
	}


	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	
	
	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}
