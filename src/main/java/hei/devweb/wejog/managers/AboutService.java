package hei.devweb.wejog.managers;

import hei.devweb.wejog.impl.AboutDaoImpl;

public class AboutService {
	
	private static AboutDaoImpl aboutDao = new AboutDaoImpl();
	
	private static class AboutServiceHolder {
		private static AboutService instance = new AboutService();
	}
	
	public static AboutService getInstance() {
		return AboutServiceHolder.instance;
	}
	
	public String getContenu(){
		return aboutDao.getContenu();
	}
	
	public static void updateAboutContenu (String aboutContenu){
		aboutDao.updateAbout(aboutContenu);
	}

}