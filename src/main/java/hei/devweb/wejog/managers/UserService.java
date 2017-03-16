package hei.devweb.wejog.managers;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.entities.User;

import hei.devweb.wejog.impl.UserDaoImpl;
import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.managers.MotDePasseManager;
import hei.devweb.wejog.managers.UserService;


public class UserService {
	
    private static class UserManagerHolder {
        private static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserManagerHolder.instance;
    }

	private static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private static Userdao UserDao = new UserDaoImpl();
	private MotDePasseManager motDePasseManager = new MotDePasseManager();


	private static class UserServiceHolder {
		private static UserService instance = new UserService();
	}

	private UserService() {
	}

	public List<User> listerUsers() {
		return UserDao.listerUsers();
	}

	public User getUser(String mail) {
		if (mail == null || "".equals(mail)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		return UserDao.getUser(mail);
	}

	/*public boolean validerMotDePasse(String email, String motDePasseAVerifier) throws WejogSecuriteException {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		if (motDePasseAVerifier == null || "".equals(motDePasseAVerifier)) {
			throw new IllegalArgumentException("Le mot de passe doit être renseigné.");
		}
		String motDePasseHashe = UserDao.getmotdepasse(email);
		if (motDePasseHashe == null) {
			throw new IllegalArgumentException("L'identifiant n'est pas connu.");
		}
		try {
			return motDePasseManager.validerMotDePasse(email, motDePasseAVerifier);
		} catch (GeneralSecurityException e) {
			throw new WejogSecuriteException("Problème dans la vérification du mot de passe.", e);
		}

	}*/
	
	public boolean validerMotDePasse(String identifiant, String motDePasse){ // a changer
		
		boolean reponse;

		System.out.println("identifiant : "+identifiant);
		System.out.println("mdp : "+motDePasse);
		System.out.println("UserDao.getmotdepasse("+identifiant+"): "+UserDao.getmotdepasse(identifiant));
		
		if (motDePasse.equals(UserDao.getmotdepasse(identifiant))){
			reponse = true;
		}
		else{
			reponse = false;
		}
		
		System.out.println(reponse);
		
		return reponse;
		
	}
	
	public static void addUser (User newUser) {
		UserDao.addUser(newUser);
	}

	public void supprimerUser(Long idusers) {
		if (idusers == null) {
			throw new IllegalArgumentException("L'id de l'user ne peut pas être null.");
		}

		UserDao.supprimerUser(idusers);
		LOGGER.info(String.format("User|supprimer|id=%d", idusers));
	}
	
	public static void updateDescription (Long idusers, String description){
		UserDao.modificationDescription(idusers, description);
	}
	
	public static String getDescription(long idusers){
		return UserDao.getDescription(idusers);
	}

}