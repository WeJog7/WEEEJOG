package hei.devweb.wejog.managers;

import java.util.List;


import hei.devweb.wejog.entities.Performance;

import hei.devweb.wejog.impl.PerformanceDaoImpl;


public class PerformanceService {private PerformanceDaoImpl performanceDao = new PerformanceDaoImpl();

private static class PerformanceServiceHolder {
	private static PerformanceService instance = new PerformanceService();
}

public static PerformanceService getInstance() {
	return PerformanceServiceHolder.instance;
}

private PerformanceService() {
}

public  List<Performance> ListPerformanceToDo(){
	return performanceDao.ListPerfomanceToDo();
}


public void addPerformance(Performance newPerformance) {
	performanceDao.addPerformance(newPerformance);
	
} 

}
