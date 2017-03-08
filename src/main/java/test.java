

import hei.devweb.wejog.dao.Userdao;
import hei.devweb.wejog.impl.UserDaoImpl;

public class test {

	public static void main(String[] args){

		Userdao UserDao = new UserDaoImpl();


		System.out.println(UserDao.getmotdepasse("test@test.fr"));
		
		System.out.println(UserDao.getUser("test@test.fr").getMotdepasse());
		
		System.out.println(UserDao.getUser("test@test.fr").getPrenom());

	}

}
