package hei.devweb.wejog.managers;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Logger;

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.entities.User;

import hei.devweb.wejog.impl.UserDaoImpl;
import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.managers.MotDePasseManager;
import hei.devweb.wejog.managers.UserService;


public class UserService {
	
    private static class UserServiceHolder {
        private static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

	private static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private static Userdao UserDao = new UserDaoImpl();
	private MotDePasseManager motDePasseManager = new MotDePasseManager();

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
	
	public User getUser(Long idusers) {
		if (idusers == null || "".equals(idusers)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		return UserDao.getUser(idusers);
	}

	public boolean validerMotDePasse(String email, String motDePasseAVerifier) throws WejogSecuriteException {
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
			return motDePasseManager.validerMotDePasse(motDePasseAVerifier, motDePasseHashe);
		} catch (GeneralSecurityException e) {
			throw new WejogSecuriteException("Problème dans la vérification du mot de passe.", e);
		}

	}
	
	public String genererMotDePasse(String motDePasse) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return motDePasseManager.genererMotDePasse(motDePasse);
	}
	
	
	public static void addUser (User newUser) {
		UserDao.addUser(newUser);
	}

	public void supprimerusers(Long idusers) {
	
		UserDao.supprimerusers(idusers);
	}
	
	public static void updateDescription (Long idusers, String description){
		UserDao.modificationDescription(idusers, description);
	}
	
	public static String getDescription(long idusers){
		return UserDao.getDescription(idusers);
	}
	
	public void updatePassword (long idusers, String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		String newPassword = motDePasseManager.genererMotDePasse(password);
		UserDao.modifierMotDePasse(idusers, newPassword);
	}
	
	public String generateRandomPassword(){
		return UserDao.generateRandomPassword();
	}

}