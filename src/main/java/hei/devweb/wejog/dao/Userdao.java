package hei.devweb.wejog.dao;

import java.util.List;

import hei.devweb.wejog.entities.User;


public interface Userdao {
	List<User> listerUsers();

	User getUser(Long idusers);

	User getUser(String mail);

	String getmotdepasse(String mail);

	void modifierMotDePasse(Long id, String motDePasse);

	User addUser(User nouvelUser);


	void supprimerusers(long idusers);


	void modificationDescription(long idusers, String description);


	String getDescription(long idusers);

}
