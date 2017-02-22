package hei.devweb.wejog.entities;



import java.io.Serializable;
import java.time.LocalDate;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = -1167387777176439635L;

	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate datedenaissance;
	private String motdepasse;
	private boolean admin;
	private boolean sexe;
	
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long id, String nom, String prenom, String email, LocalDate datedenaissance,String motdepasse,boolean sexe, boolean admin) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.datedenaissance = datedenaissance;
		this.motdepasse= motdepasse;
		this.sexe=sexe;
		this.admin = admin;
	}

	public LocalDate getDatedenaissance() {
		return datedenaissance;
	}

	public void setDatedenaissance(LocalDate datedenaissance) {
		this.datedenaissance = datedenaissance;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public boolean isSexe() {
		return sexe;
	}

	public void setSexe(boolean sexe) {
		this.sexe = sexe;
	}

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
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

	
}
