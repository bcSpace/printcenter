package solutech.printcenter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import model.DocumentMetaData;
import model.PrintDocument;
import util.GeneralUtil;

public class AppTest {
	
	@Test
	public void testUnprintableRemoval() {
		List<PrintDocument> docs = new ArrayList<>();
		docs.add(new PrintDocument(new DocumentMetaData("file.pdf"), 1));
		docs.add(new PrintDocument(new DocumentMetaData("file.asd"), 1));
		docs.add(new PrintDocument(new DocumentMetaData("file.zip"), 1));
		docs.add(new PrintDocument(new DocumentMetaData("file.file"), 1));
		List<PrintDocument> sorted = GeneralUtil.sortDocs(docs);
		assertEquals(sorted.size(), 1);
	}

	@Test
	public void testSorting() {
		Random random = new Random();
		List<PrintDocument> docs = new ArrayList<>();
		for(int i = 0; i < 100; i++) 
			docs.add(new PrintDocument(new DocumentMetaData("file"+i+".pdf"), random.nextInt(10)+1));
		//note all files unique
		
		List<PrintDocument> sortedList = GeneralUtil.sortDocs(docs);
		boolean sorted = true;
		for(int i = 1; i < sortedList.size(); i++) 
			if(sortedList.get(i).getPriority() < sortedList.get(i-1).getPriority()) {
				sorted = false;
				break;
			}
		
		assertEquals(sorted, true);
	}
	
	@Test
	public void testBucketing() {
		Random random = new Random();
		List<PrintDocument> docs = new ArrayList<>();
		for(int i = 0; i < 50; i++) 
			docs.add(new PrintDocument(new DocumentMetaData(random.nextInt(10)+".pdf"), random.nextInt(10)+1));
	
		LinkedHashMap<String, Long> buckets = new LinkedHashMap<String, Long>();
		String currentDoc = "";
		
		boolean testPassed = true;
		
		docs = GeneralUtil.sortDocs(docs);
		currentDoc = docs.get(0).getDocument().getFileName();
		buckets.put(currentDoc, docs.get(0).getPriority());
		
		for(PrintDocument doc : docs) {
			System.out.println(doc.getDocument().getFileName());
			if(buckets.containsKey(doc.getDocument().getFileName())) {
				if(doc.getPriority() < buckets.get(doc.getDocument().getFileName()))
					buckets.put(doc.getDocument().getFileName(), doc.getPriority());
				
				//meaning that the file name was already used and the next one was progressed to, if this happens it did not bucket properly
				if(!currentDoc.equals(doc.getDocument().getFileName())) {
					testPassed = false;
				}
				
			} else {
				buckets.put(doc.getDocument().getFileName(), doc.getPriority());
			}
			currentDoc = doc.getDocument().getFileName();
		}

		//check to make sure that items are sorted in order of prio
		long last = -99;
		for(String s : buckets.keySet()) {
			if(last == -99) {
				last = buckets.get(s);
				continue;
			}
			
			if(last > buckets.get(s))
				testPassed = false;
		}
		assertEquals(testPassed, true);
	}

	
	@Test
	public void testSortingSpeed() {
		//TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO 
		//Junit is not for performance testing
		Random random = new Random();
		
		long totalTime = 0;
		
		for(int i = 0; i < 10; i++) {
			List<PrintDocument> docs = new ArrayList<>();
			for(int ii = 0; ii < 1000000; ii++) 
				docs.add(new PrintDocument(new DocumentMetaData(random.nextInt(1000000)+".pdf"), random.nextInt(1000000)+1));
			
			long startTime = System.currentTimeMillis();
			List<PrintDocument> sortedDocs = GeneralUtil.sortDocs(docs);
			long timeTaken = System.currentTimeMillis()-startTime;
			totalTime+=timeTaken;
		}
		
		long averageTime = totalTime/10; 
		boolean lessThan2 = averageTime < 2000;
		assertEquals(lessThan2, true);
	}
}
