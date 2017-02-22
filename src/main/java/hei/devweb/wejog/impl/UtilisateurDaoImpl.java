package hei.devweb.wejog.impl;



import learnings.exceptions.LearningsSQLException;
import hei.devweb.wejog.entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImpl extends GenericDaoImpl {

	public List<Utilisateur> listerUtilisateurs() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT idusers, nom, prenom,datedenaissance, mail,sexe, admin FROM users ORDER BY mail");
			while (results.next()) {
				utilisateurs.add(new Utilisateur(
						results.getLong("idusers"),
						results.getString("nom"),
						results.getString("prenom"),
						results.getString("mail"),
						results.getDate("datedenaissance").toLocalDate(),
						results.getString("motdepasse"),
						results.getBoolean("sexe"),
						results.getBoolean("admin")));

			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}
		return utilisateurs;
	}

	
	


	public Utilisateur getUtilisateur(Long id) {
		Utilisateur utilisateur = null;
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT idusers, nom, prenom, email, groupe, admin FROM users WHERE idusers=?");
			stmt.setLong(1, id);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				utilisateur = (Utilisateur) listerUtilisateurs();
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}
		return utilisateur;
	}

	public Utilisateur getUtilisateur(String identifiant) {
		Utilisateur utilisateur = null;
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT idusers, nom, prenom,datedenaisse, mail, sexe, admin FROM users WHERE mail=?");
			stmt.setString(1, identifiant);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				utilisateur = (Utilisateur) listerUtilisateurs();
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}

		return utilisateur;
	}

	public String getMotDePasseUtilisateurHashe(String identifiant) {
		String motDePasse = null;
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT motdepasse FROM users WHERE mail=?");
			stmt.setString(1, identifiant);
			ResultSet results = stmt.executeQuery();
			if (results.next()) {
				motDePasse = results.getString("motdepasse");
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}

		return motDePasse;
	}

	public void modifierMotDePasse(Long id, String motDePasse) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE users SET motdepasse=? WHERE idusers=?");
			stmt.setString(1, motDePasse);
			stmt.setLong(2, id);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}
	}

	public void supprimerUtilisateur(Long id) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM  users WHERE idusers=?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}
	}

	public void modifierRoleAdmin(Long id, boolean admin) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("UPDATE users SET admin=? WHERE idusers=?");
			stmt.setBoolean(1, admin);
			stmt.setLong(2, id);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new LearningsSQLException(e);
		}
	}

	
}
