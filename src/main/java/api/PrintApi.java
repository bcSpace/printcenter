package api;

import model.PrintJob;

public class PrintApi {
	
	public static void scheduleJob(PrintJob job) {
		//scheduling code
	}
	
	public static void deleteJob(int id) {
		//delete code
	}
	
	//thread safe id grabbing
	public static synchronized int getNewId() {
		return -1; 
	}

}
