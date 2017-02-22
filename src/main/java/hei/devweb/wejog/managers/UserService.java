package hei.devweb.wejog.managers;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.entities.User;

import hei.devweb.wejog.impl.UserDaoImpl;
import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.managers.MotDePasseManager;



public class UserService {
	
	 private static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private Userdao UserDao = new UserDaoImpl();
	 private MotDePasseManager motDePasseManager = new MotDePasseManager();
	
	
    private static class UserServiceHolder {
        private static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
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
    
    public void supprimerUser(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'user ne peut pas être null.");
        }
        
        UserDao.supprimerUser(idusers);
        LOGGER.info(String.format("User|supprimer|id=%d", idusers));
    }

    public void enleverDroitsAdmin(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'utilisateur ne peut pas être null.");
        }
        UserDao.modifierRoleAdmin(idusers, false);
        LOGGER.info(String.format("User|enleverDroitsAdmin|id=%d", idusers));
    }

    public void donnerDroitsAdmin(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'utilisateur ne peut pas être null.");
        }
        UserDao.modifierRoleAdmin(idusers, true);
        LOGGER.info(String.format("Utilisateur|donnerDroitsAdmin|id=%d", idusers));
    }

}