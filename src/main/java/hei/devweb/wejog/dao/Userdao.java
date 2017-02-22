package hei.devweb.wejog.dao;

import java.util.List;

import hei.devweb.wejog.entities.User;


public interface Userdao {
	List<User> listerUsers();

	User getUser(Long idusers);

	User getUser(String mail);

	String getmotdepasse(String mail);

	void supprimerUser(Long id);

	void modifierRoleAdmin(Long id, boolean admin);

	void modifierMotDePasse(Long id, String motDePasse);

	User ajouterUser(User nouvelUser, String motDePasse);



}
