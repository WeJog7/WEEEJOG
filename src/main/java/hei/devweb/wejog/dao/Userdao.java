package hei.devweb.wejog.dao;

import java.util.List;

import hei.devweb.wejog.entities.User;


public interface Userdao {
	List<User> listerUsers();

	User getUser(String mail);
	
	User getUser(long idusers);

	String getmotdepasse(String mail);

	User addUser(User nouvelUser);

	void supprimerusers(long idusers);

	void modificationDescription(long idusers, String description);

	String getDescription(long idusers);

	void modifierMotDePasse(long idusers, String motdepasse);
	
	String generateRandomPassword();

	List<User> ListSearchAmi(String identity);

}
