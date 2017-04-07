package hei.devweb.wejog.entities;

public class CoupleAmis {
	
	public long idami;
	public long idusers1;
	public long idusers2;
	
	public CoupleAmis(long idami, long idusers1, long idusers2){
		this.idami=idami;
		this.idusers1=idusers1;
		this.idusers2=idusers2;
		
	}

	public long getIdusers1() {
		return idusers1;
	}

	public void setIdusers1(long idusers1) {
		this.idusers1 = idusers1;
	}

	public long getIdusers2() {
		return idusers2;
	}

	public void setIdusers2(long idusers2) {
		this.idusers2 = idusers2;
	}

	public long getIdami() {
		return idami;
	}

	public void setIdami(long idami) {
		this.idami = idami;
	}
	

}
