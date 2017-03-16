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

<<<<<<< HEAD
	void supprimerusers(long idusers);

=======
	void modificationDescription(long idusers, String description);
>>>>>>> branch 'master' of https://github.com/WeJog7/WEEEJOG.git

	String getDescription(long idusers);

}
