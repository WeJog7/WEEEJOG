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

	public void deleteFriend(long idusers1, long idusers2){
		amiDao.deleteFriend(idusers1, idusers2);
	}


	public void sendInvitationToBeFriend(CoupleAmis friends ){
		amiDao.sendInvitationToBeFriend(friends);
	}
	
	public void acceptFiend(CoupleAmis friends ){
		amiDao.acceptFiend(friends);
	}

	public void deleteInvitation(long idusers1, long idusers2){
		amiDao.deleteInvitation(idusers1, idusers2);
	}

	public List<CoupleAmis> ListAsking(long idusers2){
		return amiDao.ListAsking(idusers2);
	}

	public int countAsking(long idusers2){
		return amiDao.countAsking(idusers2);
	}
	
	public CoupleAmis getFriendCouple(long idusers, long idFriend){
		return amiDao.getFriendCouple(idusers, idFriend);
	}
	
	public CoupleAmis getInvitation(long idusers, long idFriend){
		return amiDao.getInvitation(idusers, idFriend);
	}
}
