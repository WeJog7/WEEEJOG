package hei.devweb.wejog.managers;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

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
		String motDePasseHashe = UserDao.getPassword(email);
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

	public void deleteUser(Long idusers) {
		UserDao.deleteUser(idusers);
	}
	
	public static void updateDescription (Long idusers, String description){
		UserDao.updateDescription(idusers, description);
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

	public List<Long> listUsersIdFound(String identity, Long idusers){
		 return UserDao.listUsersIdFound(identity, idusers); 
	 }

	public void updatePicture(long idusers, String picturePath){
		UserDao.updatePicture(idusers, picturePath);
	}
	
	public void blockUser(long idusers){
		UserDao.blockUser(idusers);
	}
	
	public List<User> usersBlockList() {
		return UserDao.usersBlockList();
	}
	
	public void unblockUser(long idusers){
		UserDao.unblockUser(idusers);
	}
	
	public static void addTemporaryUser(User newuser, String activationKey) {
		UserDao.addTemporaryUser(newuser, activationKey);
	}
	
	public User getTemporaryUser(Long idAccountNotActivated) {
		return UserDao.getTemporaryUser(idAccountNotActivated);
	}
	
	public static void deleteTemporaryUser(long idAccountNotActivated) {
		UserDao.deleteTemporaryUser(idAccountNotActivated);
	}
	
	public String generateActivationKey() {
		return UserDao.generateActivationKey();
	}
	
	public User getTemporaryUser(String mail) {
		return UserDao.getTemporaryUser(mail);
	}

}