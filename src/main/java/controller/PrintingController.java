package controller;

import java.util.Date;
import java.util.List;

import api.PrintApi;
import model.PrintDocument;
import model.PrintJob;
import util.GeneralUtil;

public class PrintingController {
	
	public void createNewJob(PrintJob job) {
		job.setDocuments(GeneralUtil.sortDocs(job.getDocuments()));
		job.setJobId(PrintApi.getNewId());
		PrintApi.scheduleJob(job);
	}
	
	public void mergeJobs(PrintJob job1, PrintJob job2, String target, Date time) {
		PrintJob job = new PrintJob();
		job.setTarget(target);
		job.setTime(time);
		
		List<PrintDocument> docs = GeneralUtil.mergeDocs(job1.getDocuments(), job2.getDocuments());
		job.setDocuments(docs);
		job.setJobId(PrintApi.getNewId());
		
		PrintApi.deleteJob(job1.getJobId());
		PrintApi.deleteJob(job2.getJobId());
		PrintApi.scheduleJob(job);
	}

}
