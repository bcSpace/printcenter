package main;

import java.util.ArrayList;
import java.util.Date;

import controller.PrintingController;
import model.DocumentMetaData;
import model.PrintDocument;
import model.PrintJob;

public class Main {
	
	private final PrintingController controller;

	Main() {
		
		controller = new PrintingController();
		
		PrintJob job = new PrintJob();
		ArrayList<PrintDocument> docs = new ArrayList<>();
		PrintDocument doc = new PrintDocument(new DocumentMetaData("gottem.pdf"), 1);
		docs.add(new PrintDocument(new DocumentMetaData("gottem.pdf"), 2));
		docs.add(new PrintDocument(new DocumentMetaData("ayy.pdf"), 3));
		docs.add(new PrintDocument(new DocumentMetaData("gg.pdf"), 1));
		docs.add(new PrintDocument(new DocumentMetaData("ss.pdf"), 5));
		docs.add(new PrintDocument(new DocumentMetaData("gg.asdsa"), 3));
		docs.add(new PrintDocument(new DocumentMetaData("gg.pdf"), 4));
		job.setDocuments(docs);
		job.setTarget("Printer");
		job.setTime(new Date());
		
		controller.createNewJob(job);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
