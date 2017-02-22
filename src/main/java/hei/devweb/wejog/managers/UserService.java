package hei.devweb.wejog.managers;

import java.util.List;
import java.util.logging.Logger;

import hei.devweb.wejog.entities.User;

import hei.devweb.wejog.impl.UserDaoImpl;



public class UserService {
	
	 private static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private UserDaoImpl UserDao = new UserDaoImpl();
	
	
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
    
    
    
    
    
    
    public void supprimerUser(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'user ne peut pas être null.");
        }
        
        UserDao.supprimerusers(idusers);
        LOGGER.info(String.format("User|supprimer|id=%d", idusers));
    }

    public void enleverDroitsAdmin(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'utilisateur ne peut pas être null.");
        }
        UserDao.modifierroleadmin(idusers, false);
        LOGGER.info(String.format("User|enleverDroitsAdmin|id=%d", idusers));
    }

    public void donnerDroitsAdmin(Long idusers) {
        if (idusers == null) {
            throw new IllegalArgumentException("L'id de l'utilisateur ne peut pas être null.");
        }
        UserDao.modifierroleadmin(idusers, true);
        LOGGER.info(String.format("Utilisateur|donnerDroitsAdmin|id=%d", idusers));
    }

}