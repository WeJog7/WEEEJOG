

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.impl.UserDaoImpl;
import hei.devweb.wejog.managers.UserService;

public class test {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{

		/*Userdao UserDao = new UserDaoImpl();
		System.out.println(UserDao.getmotdepasse("test@test.fr"));
		System.out.println(UserDao.getUser("test@test.fr").getMotdepasse());
		System.out.println(UserDao.getUser("test@test.fr").getPrenom());*/
		
		System.out.println(UserService.getInstance().genererMotDePasse("1111"));

	}

}
