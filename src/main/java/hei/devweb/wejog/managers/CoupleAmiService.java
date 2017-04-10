package hei.devweb.wejog.managers;

import java.util.List;

import hei.devweb.wejog.entities.CoupleAmis;
import hei.devweb.wejog.impl.CoupleAmiDaoImpl;

public class CoupleAmiService {
	
private CoupleAmiDaoImpl amiDao = new CoupleAmiDaoImpl();
	
	private static class AmiServiceHolder {
		private static CoupleAmiService instance = new CoupleAmiService();
	}

	public static CoupleAmiService getInstance() {
		return AmiServiceHolder.instance;
	}
	
	public  List<CoupleAmis> ListAmis(long idusers){
		return amiDao.ListAmis(idusers);
		
	}
	
	public void supprimeramis(long idusers1, long idusers2){
		amiDao.supprimeramis(idusers1, idusers2);
	}
	

	public void addFriend(CoupleAmis friends ){
		amiDao.addFriend(friends);
	}
	public void acceptedFiend(CoupleAmis friends ){
		amiDao.acceptedFiend(friends);
	}
	 public void deleteAsking(long idusers1, long idusers2){
		 amiDao.deleteAsking(idusers1, idusers2);
	 }
	public List<CoupleAmis> ListAsking(long idusers2){
		return amiDao.ListAsking(idusers2);
	}
	
	public int countAsking(long idusers2){
		return amiDao.countAsking(idusers2);
	}
}
