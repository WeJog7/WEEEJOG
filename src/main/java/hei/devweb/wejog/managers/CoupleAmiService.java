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
	

}
