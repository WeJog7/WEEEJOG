package hei.devweb.wejog.managers;

import java.sql.Date;
import java.util.List;
import hei.devweb.wejog.entities.Performance;
import hei.devweb.wejog.impl.PerformanceDaoImpl;

public class PerformanceService {

	private PerformanceDaoImpl performanceDao = new PerformanceDaoImpl();

	private static class PerformanceServiceHolder {
		private static PerformanceService instance = new PerformanceService();
	}

	public static PerformanceService getInstance() {
		return PerformanceServiceHolder.instance;
	}

	private PerformanceService() {
	}

	public  List<Performance> ListPerformanceToDo(long idusers){
		return performanceDao.ListPerfomanceToDo(idusers);
	}


	public void addPerformance(Performance newPerformance) {
		performanceDao.addPerformance(newPerformance);

	} 
	public void supprimerPerformance(Long idperformance) {
		performanceDao.supprimerPerformance(idperformance);
	}
	
	public Performance getperformance(Long idperformance){
		return performanceDao.getperformance(idperformance);
	}
	
	public List<Performance> friendsPerformances(long idusers){
		return performanceDao.friendsPerformances(idusers);
	}
	
	public int countTimePerformance(long userCreatorId){
		return performanceDao.countTimePerformance(userCreatorId);
			
	}
	
	public int countDistancePerformance (long userCreatorId){
		return performanceDao.countDistance(userCreatorId);
	}
	
	public int countNumberOfRace (long userCreatorId){
		return performanceDao.countNumberOfRace(userCreatorId);
	}
	
	public int getperformanceWeek1KM(long userCreatorId, Date todayDate){
		return performanceDao.getperformanceWeek1KM(userCreatorId, todayDate);
		}
	public int getperformanceWeek5KM(long userCreatorId, Date todayDate){
		return performanceDao.getperformanceWeek5KM(userCreatorId, todayDate);
		}
	public int getperformanceWeek10KM(long userCreatorId, Date todayDate){
		return performanceDao.getperformanceWeek10KM(userCreatorId, todayDate);
		}
	public int getperformanceWeek42KM(long userCreatorId, Date todayDate){
		return performanceDao.getperformanceWeek42KM(userCreatorId, todayDate);
		}
}
