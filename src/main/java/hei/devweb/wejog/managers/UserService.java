package hei.devweb.wejog.managers;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;

import hei.devweb.wejog.entities.User;
import hei.devweb.wejog.exceptions.WejogSecuriteException;
import hei.devweb.wejog.impl.UserDaoImpl;



public class UserService {
	
	 private static Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private UserDaoImpl UserDao = new UserDaoImpl();
	private MotDePasseService motDePasseService = new MotDePasseService();
	
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
    
    
    public boolean validerMotDePasse(String mail, String motDePasseAVerifier) throws WejogSecuriteException {
        if (mail == null || "".equals(mail)) {
            throw new IllegalArgumentException("L'identifiant doit être renseigné.");
        }
        if (motDePasseAVerifier == null || "".equals(motDePasseAVerifier)) {
            throw new IllegalArgumentException("Le mot de passe doit être renseigné.");
        }
        String motDePasseHashe = UserDao.getmotdepasse(mail);
        if (motDePasseHashe == null) {
            throw new IllegalArgumentException("L'identifiant n'est pas connu.");
        }
        try {
            return motDePasseService.validerMotDePasse(motDePasseAVerifier, motDePasseHashe);
        } catch (GeneralSecurityException e) {
            throw new WejogSecuriteException("Problème dans la vérification du mot de passe.", e);
        }
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

    /**
     * Réinitialise le mot de passe de l'utilisateur avec pour valeur son email
     */
    public void reinitialiserMotDePasse(Long idusers) throws WejogSecuriteException {
        if (idusers == null) {
            throw new IllegalArgumentException("L'identifiant de l'utilisateur ne peut pas être null.");
        }
        User user = UserDao.getUser(idusers);
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur n'est pas connu.");
        }
        try {
            String nouveauMotDePasse = motDePasseService.genererMotDePasse(user.getMail());
            UserDao.modifiermotdepasse(idusers, nouveauMotDePasse);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new WejogSecuriteException("Problème dans la génération du mot de passe.", e);
        }
        LOGGER.info(String.format("Utilisateur|reinitialiserMotDePasse|id=%d", idusers));
    }

    public User ajouterUtilisateur(User user) throws WejogSecuriteException {
        if (user.getMail() == null || "".equals(user.getMail())) {
            throw new IllegalArgumentException("L'adresse email doit être renseigné.");
        }
        User utilisateurExistant = this.getUser(user.getMail());
        if (utilisateurExistant != null) {
            throw new IllegalArgumentException("L'identifiant est déjà utilisé.");
        }

        String motdepasse;
        try {
            motdepasse = motDePasseService.genererMotDePasse(user.getMail());
        } catch (GeneralSecurityException e) {
            throw new WejogSecuriteException("Problème dans la génération du mot de passe.");
        }
        User nouvelUtilisateur = UserDao.addUser(user, motdepasse);

        LOGGER.info(String.format("Utilisateur|ajouterUtilisateur|id=%d;mail=%s", nouvelUtilisateur.getIdusers(), nouvelUtilisateur.getMail()));
        return nouvelUtilisateur;
    }

    public void modifierMotDePasse(Long id, String motDePasse, String confirmationMotDePasse) throws WejogSecuriteException {
        if (motDePasse == null || "".equals(motDePasse) || confirmationMotDePasse == null || "".equals(confirmationMotDePasse)) {
            throw new IllegalArgumentException("Les mots de passe doivent être renseignés.");
        }
        if (!motDePasse.equals(confirmationMotDePasse)) {
            throw new IllegalArgumentException("La confirmation du mot de passe ne correspond pas.");
        }

        try {
            String motDePasseHashe = motDePasseService.genererMotDePasse(motDePasse);
            UserDao.modifiermotdepasse(id, motDePasseHashe);
        } catch (GeneralSecurityException e) {
            throw new WejogSecuriteException("Problème dans la génération du mot de passe.", e);
        }
        LOGGER.info(String.format("Utilisateur|modifierMotDePasse|id=%d", id));
	}
    }
