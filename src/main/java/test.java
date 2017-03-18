

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
		
		/*System.out.println(UserService.getInstance().genererMotDePasse("1111"));*/
		
		System.out.println(generate(7));

	}
	
	public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
	}

}
