package model;

import java.util.Date;
import java.util.List;

public class PrintJob {
	
	private int jobId;
	
	private List<PrintDocument> documents; //unsorted or sorted list of documents 
	
	//target place and time to print, could possibly wrap into class
	private String target;
	private Date time;
	
	public List<PrintDocument> getDocuments() {
		return documents;
	}
	public void setDocuments(List<PrintDocument> documents) {
		this.documents = documents;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	} 
}
